package br.com.events.event.event.adapter.port.controller;

import br.com.events.event.event.adapter.port.QuoteRequestControllerV1Port;
import br.com.events.event.event.business.use_case.quote_request.FindAllQuoteRequestUseCase;
import br.com.events.event.event.data.io.inbound.quote.response.QuoteRequestTypeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/v1/quote-request")
@RequiredArgsConstructor
public class QuoteRequestControllerV1 implements QuoteRequestControllerV1Port {

    private final FindAllQuoteRequestUseCase findAllQuoteRequestUseCase;

    @Override
    @GetMapping("/{eventUuid}")
    public ResponseEntity<List<QuoteRequestTypeResponse>> findAll(@PathVariable("eventUuid") String eventUuid) {
        var quoteRequests = findAllQuoteRequestUseCase.execute(eventUuid);
        return ResponseEntity.ok(quoteRequests);
    }
}
