package br.com.events.event.event.business.use_case.quote_request.decline;

import br.com.events.event.event.data.io.inbound.quote.request.decline.DeclineQuoteRequestRequest;

public interface DeclineQuoteRequestUseCase {

    void execute(String quoteRequestUuid, DeclineQuoteRequestRequest declineQuoteRequest);
}
