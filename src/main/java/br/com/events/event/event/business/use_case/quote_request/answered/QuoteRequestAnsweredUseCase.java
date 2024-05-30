package br.com.events.event.event.business.use_case.quote_request.answered;

import br.com.events.event.event.data.io.outbound.ms_band.message.quote.QuoteAnsweredMessage;

public interface QuoteRequestAnsweredUseCase {

    void execute(QuoteAnsweredMessage quoteAnsweredMessage);
}
