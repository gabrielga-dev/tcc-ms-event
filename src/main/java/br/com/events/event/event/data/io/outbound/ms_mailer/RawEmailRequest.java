package br.com.events.event.event.data.io.outbound.ms_mailer;

import br.com.events.event.event.core.util.AuthUtil;
import br.com.events.event.event.core.util.DateUtil;
import br.com.events.event.event.data.io.inbound.quote.request.decline.DeclineQuoteRequestRequest;
import br.com.events.event.event.data.io.outbound.msAuth.person.findByUuid.out.PersonResponse;
import br.com.events.event.event.data.model.Event;
import br.com.events.event.event.data.model.QuoteRequest;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

@Getter
@ToString
@RequiredArgsConstructor
public class RawEmailRequest implements Serializable {

    private final EmailRequestType type;
    private final Map<String, String> keyAndValues;

    public RawEmailRequest(Event event, PersonResponse person) {
        this.type = EmailRequestType.BAND_QUOTE_REQUEST_DELETED;
        this.keyAndValues = Map.of(
                "email", person.getEmail(),
                "eventName", event.getName()
        );
    }

    public RawEmailRequest(QuoteRequest quoteRequest, DeclineQuoteRequestRequest declineQuoteRequest) {
        this.type = EmailRequestType.QUOTE_REQUEST_DECLINED;
        this.keyAndValues = Map.of(
                "email", AuthUtil.getAuthenticatedPerson().getEmail(),
                "eventName", quoteRequest.getEvent().getName(),
                "eventDate", DateUtil.format(quoteRequest.getEvent().getDate()),
                "businessTypeName", declineQuoteRequest.getBusinessType().getTranslatedSingleName(),
                "businessName", declineQuoteRequest.getBusinessName()
        );
    }
}
