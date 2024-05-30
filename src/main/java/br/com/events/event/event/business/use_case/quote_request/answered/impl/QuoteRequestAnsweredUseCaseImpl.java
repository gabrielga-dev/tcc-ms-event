package br.com.events.event.event.business.use_case.quote_request.answered.impl;

import br.com.events.event.event.business.command.quote_request.FindQuoteRequestCommand;
import br.com.events.event.event.business.command.quote_request.SaveQuoteRequestCommand;
import br.com.events.event.event.business.command.quote_request.message.answered.SendQuoteRequestAnsweredMessageCommand;
import br.com.events.event.event.business.use_case.quote_request.answered.QuoteRequestAnsweredUseCase;
import br.com.events.event.event.core.exception.quote_request.QuoteRequestDoesNotExistsException;
import br.com.events.event.event.data.io.outbound.ms_band.message.quote.QuoteAnsweredMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;

@Slf4j
@Component
@RequiredArgsConstructor
public class QuoteRequestAnsweredUseCaseImpl implements QuoteRequestAnsweredUseCase {

    private final FindQuoteRequestCommand findQuoteRequestCommand;
    private final SaveQuoteRequestCommand saveQuoteRequestCommand;
    private final SendQuoteRequestAnsweredMessageCommand sendQuoteRequestAnsweredMessageCommand;

    @Override
    @Transactional
    public void execute(QuoteAnsweredMessage quoteAnsweredMessage) {
        var quoteRequest = findQuoteRequestCommand.findByUuid(quoteAnsweredMessage.getMsEventQuoteUuid())
                .orElseThrow(QuoteRequestDoesNotExistsException::new);

        if (!quoteRequest.getEvent().getActive() || quoteRequest.getEvent().alreadyHappened()) {
            log.error(
                    "Quote created to a past event. quote_request_uuid: {}; event_uuid {}",
                    quoteRequest.getUuid(),
                    quoteRequest.getEvent().getUuid()
            );
            return;
        }
        quoteRequest.update(quoteAnsweredMessage);
        quoteRequest = saveQuoteRequestCommand.execute(quoteRequest);

        sendQuoteRequestAnsweredMessageCommand.sendMessage(quoteRequest, quoteAnsweredMessage);
    }
}
