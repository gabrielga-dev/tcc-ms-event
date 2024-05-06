package br.com.events.event.event.business.use_case.quote_request.delete.impl;

import br.com.events.event.event.business.command.quote_request.DeleteQuoteRequestCommand;
import br.com.events.event.event.business.command.quote_request.FindQuoteRequestCommand;
import br.com.events.event.event.business.use_case.quote_request.delete.DeleteQuoteRequestUseCase;
import br.com.events.event.event.core.exception.quote_request.QuoteRequestDoesNotExistsException;
import br.com.events.event.event.data.io.outbound.ms_band.message.quote_request.QuoteRequestCreationErrorMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DeleteQuoteRequestUseCaseImpl implements DeleteQuoteRequestUseCase {

    private final FindQuoteRequestCommand findQuoteRequestCommand;
    private final DeleteQuoteRequestCommand deleteQuoteRequestCommand;

    @Override
    public void execute(QuoteRequestCreationErrorMessage object) {
        findQuoteRequestCommand.findByUuid(object.getQuoteRequestUuid())
                .ifPresentOrElse(
                        quoteRequest -> {
                            deleteQuoteRequestCommand.execute(object.getQuoteRequestUuid());
                            log.info("[SUCCESS] quote request found and deleted! {}", object.getQuoteRequestUuid());
                        },
                        () -> {
                            throw new QuoteRequestDoesNotExistsException();
                        }
                );
    }
}
