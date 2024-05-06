package br.com.events.event.event.data.io.inbound.quote.response;

import br.com.events.event.event.data.model.QuoteRequest;
import br.com.events.event.event.data.model.type.BusinessType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
public class QuoteRequestTypeResponse {

    private String name;
    private BusinessType businessType;
    private List<QuoteRequestResponse> quoteRequests;

    public QuoteRequestTypeResponse(Map.Entry<BusinessType, List<QuoteRequest>> entry) {
        this.name = entry.getKey().getTranslatedName();
        this.businessType = entry.getKey();
        this.quoteRequests = entry.getValue()
                .stream()
                .map(QuoteRequestResponse::new)
                .collect(Collectors.toList());
    }
}
