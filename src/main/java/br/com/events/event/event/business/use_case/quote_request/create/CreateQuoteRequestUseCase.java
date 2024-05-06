package br.com.events.event.event.business.use_case.quote_request.create;

import br.com.events.event.event.data.model.type.BusinessType;

public interface CreateQuoteRequestUseCase {

    BusinessType getHandledBusinessType();

    void execute(String eventUuid, String businessUuid, Object quoteRequest);
}
