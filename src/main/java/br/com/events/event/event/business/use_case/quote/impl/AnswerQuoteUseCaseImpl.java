package br.com.events.event.event.business.use_case.quote.impl;

import br.com.events.event.event.business.command.quote.SendQuoteEventAnswerMessageCommand;
import br.com.events.event.event.business.command.quote_request.FindQuoteRequestCommand;
import br.com.events.event.event.business.command.quote_request.SaveQuoteRequestCommand;
import br.com.events.event.event.business.use_case.quote.AnswerQuoteUseCase;
import br.com.events.event.event.core.exception.event.EventAlreadyHappenedException;
import br.com.events.event.event.core.exception.event.EventDoesNotExistsException;
import br.com.events.event.event.core.exception.event.NotEventOwnerException;
import br.com.events.event.event.core.exception.quote_request.QuoteRequestAlreadyAnsweredException;
import br.com.events.event.event.core.exception.quote_request.QuoteRequestDoesNotExistsException;
import br.com.events.event.event.core.exception.quote_request.QuoteRequestNotAnsweredByBusinessException;
import br.com.events.event.event.core.util.AuthUtil;
import br.com.events.event.event.data.model.type.QuoteRequestStatusType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class AnswerQuoteUseCaseImpl implements AnswerQuoteUseCase {

    private final FindQuoteRequestCommand findQuoteRequestCommand;
    private final SaveQuoteRequestCommand saveQuoteRequestCommand;
    private final SendQuoteEventAnswerMessageCommand sendQuoteEventAnswerMessageCommand;

    @Override
    public void execute(String quoteRequestUuid, boolean hired) {
        var quoteRequest = findQuoteRequestCommand.findByUuid(quoteRequestUuid)
                .orElseThrow(QuoteRequestDoesNotExistsException::new);

        if (!Objects.equals(AuthUtil.getAuthenticatedPerson().getUuid(), quoteRequest.getEvent().getOwnerUuid())) {
            throw new NotEventOwnerException();
        }
        if (!Objects.equals(quoteRequest.getStatus(), QuoteRequestStatusType.ANSWERED)) {
            throw new QuoteRequestAlreadyAnsweredException();
        }
        if (Objects.equals(quoteRequest.getStatus(), QuoteRequestStatusType.NON_ANSWERED)) {
            throw new QuoteRequestNotAnsweredByBusinessException();
        }
        if (!quoteRequest.getEvent().getActive()) {
            throw new EventDoesNotExistsException();
        }
        if (quoteRequest.getEvent().alreadyHappened()) {
            throw new EventAlreadyHappenedException();
        }
        quoteRequest.decline(hired);
        quoteRequest = saveQuoteRequestCommand.execute(quoteRequest);

        //only bands
        sendQuoteEventAnswerMessageCommand.execute(quoteRequest, hired);
    }
}
