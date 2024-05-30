package br.com.events.event.event.data.io.inbound.quote.response;

import br.com.events.event.event.data.model.QuoteRequest;
import br.com.events.event.event.data.model.type.QuoteRequestStatusType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuoteRequestResponse {

    private String serviceUuid;
    private String quoteUuid;
    private String statusDescription;
    private QuoteRequestStatusType status;


    public QuoteRequestResponse(QuoteRequest quoteRequest) {
        this.serviceUuid = quoteRequest.getBusinessUuid();
        this.quoteUuid = quoteRequest.getQuoteUuid();
        this.statusDescription = quoteRequest.getStatus().getTranslatedName();
        this.status = quoteRequest.getStatus();
    }
}
