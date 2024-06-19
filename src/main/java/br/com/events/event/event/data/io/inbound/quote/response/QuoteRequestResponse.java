package br.com.events.event.event.data.io.inbound.quote.response;

import br.com.events.event.event.core.util.BigDecimalUtil;
import br.com.events.event.event.data.model.QuoteRequest;
import br.com.events.event.event.data.model.type.QuoteRequestStatusType;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class QuoteRequestResponse {

    private String serviceUuid;
    private String quoteUuid;
    private String quoteRequestUuid;
    private String statusDescription;
    private QuoteRequestStatusType status;
    private String price;
    private String observation;


    public QuoteRequestResponse(QuoteRequest quoteRequest) {
        this.serviceUuid = quoteRequest.getBusinessUuid();
        this.quoteUuid = quoteRequest.getQuoteUuid();
        this.quoteRequestUuid = quoteRequest.getUuid();
        this.statusDescription = quoteRequest.getStatus().getTranslatedName();
        this.status = quoteRequest.getStatus();
        this.price = BigDecimalUtil.format(quoteRequest.getPrice());
        if (
                Objects.nonNull(quoteRequest.getBusinessObservation()) &&
                        !quoteRequest.getBusinessObservation().isBlank()
        ) {
            this.observation = "Obs.: " + quoteRequest.getBusinessObservation();
        }
    }
}
