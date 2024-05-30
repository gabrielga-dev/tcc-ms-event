package br.com.events.event.event.business.command.quote_request.message.answered;

import br.com.events.event.event.data.io.outbound.ms_band.message.quote.QuoteAnsweredMessage;
import br.com.events.event.event.data.model.QuoteRequest;

public interface SendQuoteRequestAnsweredMessageCommand {

    void sendMessage(QuoteRequest quoteRequest, QuoteAnsweredMessage quoteAnsweredMessage);
}
