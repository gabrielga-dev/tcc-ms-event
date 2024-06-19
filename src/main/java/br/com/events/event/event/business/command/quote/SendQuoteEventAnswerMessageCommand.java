package br.com.events.event.event.business.command.quote;

import br.com.events.event.event.data.model.QuoteRequest;

public interface SendQuoteEventAnswerMessageCommand {

    void execute(QuoteRequest quoteRequest, boolean hired);
}
