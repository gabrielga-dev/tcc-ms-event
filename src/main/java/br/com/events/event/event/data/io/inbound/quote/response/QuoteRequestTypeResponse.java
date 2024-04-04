package br.com.events.event.event.data.io.inbound.quote.response;

import br.com.events.event.event.data.model.QuoteRequest;
import br.com.events.event.event.data.model.type.ServiceType;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Getter
@Setter
public class QuoteRequestTypeResponse {

    private String name;
    private ServiceType serviceType;
    private List<QuoteRequestResponse> quoteRequests;

    public QuoteRequestTypeResponse(Map.Entry<ServiceType, List<QuoteRequest>> entry) {
        this.name = entry.getKey().getTranslatedName();
        this.serviceType = entry.getKey();
        this.quoteRequests = entry.getValue()
                .stream()
                .map(QuoteRequestResponse::new)
                .collect(Collectors.toList());
    }
}
