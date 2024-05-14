package br.com.events.event.event.business.use_case.quote_request.decline.impl;

import br.com.events.event.event.business.command.quote_request.FindQuoteRequestCommand;
import br.com.events.event.event.business.command.quote_request.SaveQuoteRequestCommand;
import br.com.events.event.event.business.command.quote_request.message.decline.SendDeclinedQuoteRequestEmailCommand;
import br.com.events.event.event.business.use_case.quote_request.decline.DeclineQuoteRequestUseCase;
import br.com.events.event.event.core.exception.quote_request.QuoteRequestDoesNotExistsException;
import br.com.events.event.event.data.io.inbound.quote.request.decline.DeclineQuoteRequestRequest;
import br.com.events.event.event.data.model.type.QuoteRequestStatusType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class DeclineQuoteRequestUseCaseImpl implements DeclineQuoteRequestUseCase {

    private final FindQuoteRequestCommand findQuoteRequestCommand;
    private final SaveQuoteRequestCommand saveQuoteRequestCommand;
    private final SendDeclinedQuoteRequestEmailCommand quoteRequestEmailCommand;

    @Override
    public void execute(String quoteRequestUuid, DeclineQuoteRequestRequest declineQuoteRequest) {
        var quoteRequest = findQuoteRequestCommand.findByUuid(quoteRequestUuid)
                .orElseThrow(QuoteRequestDoesNotExistsException::new);

        if (!Objects.equals(QuoteRequestStatusType.NON_ANSWERED, quoteRequest.getStatus())) {
            throw new QuoteRequestDoesNotExistsException();
        }

        quoteRequest.decline();
        saveQuoteRequestCommand.execute(quoteRequest);

        quoteRequestEmailCommand.sendMessage(quoteRequest, declineQuoteRequest);
    }
}
