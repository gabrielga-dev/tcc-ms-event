package br.com.events.event.event.business.use_case.quote_request.create;

import br.com.events.event.event.data.model.type.BusinessType;

public interface CreateQuoteRequestFactory {

    void execute(String eventUuid, BusinessType businessType, String businessUuid, Object quoteRequest);
}
