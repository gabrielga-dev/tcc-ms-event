package br.com.events.event.event.adapter.port.controller;

import br.com.events.event.event.adapter.port.QuoteRequestControllerV1Port;
import br.com.events.event.event.business.use_case.quote_request.FindAllQuoteRequestUseCase;
import br.com.events.event.event.business.use_case.quote_request.create.CreateQuoteRequestFactory;
import br.com.events.event.event.business.use_case.quote_request.decline.DeclineQuoteRequestUseCase;
import br.com.events.event.event.data.io.inbound.quote.request.create.QuoteRequestRequest;
import br.com.events.event.event.data.io.inbound.quote.request.decline.DeclineQuoteRequestRequest;
import br.com.events.event.event.data.io.inbound.quote.response.QuoteRequestTypeResponse;
import br.com.events.event.event.data.model.type.BusinessType;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/quote-request")
@RequiredArgsConstructor
public class QuoteRequestControllerV1 implements QuoteRequestControllerV1Port {

    private final FindAllQuoteRequestUseCase findAllQuoteRequestUseCase;
    private final CreateQuoteRequestFactory createQuoteRequestFactory;
    private final DeclineQuoteRequestUseCase declineQuoteRequestUseCase;

    @Override
    @GetMapping("/{eventUuid}")
    public ResponseEntity<List<QuoteRequestTypeResponse>> findAll(@PathVariable("eventUuid") String eventUuid) {
        var quoteRequests = findAllQuoteRequestUseCase.execute(eventUuid);
        return ResponseEntity.ok(quoteRequests);
    }

    @Override
    @PostMapping("/{eventUuid}/band/{bandUuid}")
    public ResponseEntity<Void> createForBand(
            @PathVariable("eventUuid") String eventUuid,
            @PathVariable("bandUuid") String bandUuid,
            @RequestBody @Valid QuoteRequestRequest quoteRequest
    ) {
        createQuoteRequestFactory.execute(eventUuid, BusinessType.BAND, bandUuid, quoteRequest);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PutMapping("/{uuid}/decline")
    @PreAuthorize("hasAuthority('BAND')")
    public ResponseEntity<Void> decline(
            @PathVariable("uuid") String quoteRequestUuid,
            @RequestBody @Valid DeclineQuoteRequestRequest declineQuoteRequest
    ) {
        declineQuoteRequestUseCase.execute(quoteRequestUuid, declineQuoteRequest);
        return ResponseEntity.noContent().build();
    }
}
