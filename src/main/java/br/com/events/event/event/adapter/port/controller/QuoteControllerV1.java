package br.com.events.event.event.adapter.port.controller;

import br.com.events.event.event.adapter.port.QuoteControllerV1Port;
import br.com.events.event.event.business.use_case.quote.AnswerQuoteUseCase;
import br.com.events.event.event.business.use_case.quote.GenerateQuoteContractUseCase;
import br.com.events.event.event.core.util.FileUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/quote")
@RequiredArgsConstructor
public class QuoteControllerV1 implements QuoteControllerV1Port {

    private final AnswerQuoteUseCase answerQuoteUseCase;
    private final GenerateQuoteContractUseCase generateQuoteContractUseCase;

    @Override
    @PostMapping("/{uuid}/accept")
    @PreAuthorize("hasAuthority('CONTRACTOR')")
    public ResponseEntity<Void> accept(@PathVariable("uuid") String quoteRequestUuid) {
        answerQuoteUseCase.execute(quoteRequestUuid, true);
        return ResponseEntity.noContent().build();
    }

    @Override
    @DeleteMapping("/{uuid}/decline")
    @PreAuthorize("hasAuthority('CONTRACTOR')")
    public ResponseEntity<Void> decline(@PathVariable("uuid") String quoteRequestUuid) {
        answerQuoteUseCase.execute(quoteRequestUuid, false);
        return ResponseEntity.noContent().build();
    }

    @Override
    @GetMapping("/{uuid}/contract")
    @PreAuthorize("hasAnyAuthority('BAND', 'CONTRACTOR')")
    public ResponseEntity<InputStreamResource> generateContract(@PathVariable("uuid") String quoteRequestUuid) {
        var pdf = generateQuoteContractUseCase.execute(quoteRequestUuid);
        return FileUtil.output(pdf.getFileBytes(), pdf.getFileName());
    }
}
