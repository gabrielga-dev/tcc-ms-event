package br.com.events.event.event.business.command.quote_request.message;

import br.com.events.event.event.data.io.inbound.quote.request.QuoteRequestRequest;
import br.com.events.event.event.data.model.type.BusinessType;

public interface SendQuoteRequestMessageCommand {

    BusinessType handledBusinessType();

    void sendMessage(
            String eventUuid,
            String quoteRequestUuid,
            String businessUuid,
            Object quoteRequest,
            boolean isSuccess
    );
}
