package br.com.events.event.event.business.use_case.quote_request;

import br.com.events.event.event.data.io.inbound.quote.response.QuoteRequestTypeResponse;

import java.util.List;

public interface FindAllQuoteRequestUseCase {

    List<QuoteRequestTypeResponse> execute(String eventUuid);
}
