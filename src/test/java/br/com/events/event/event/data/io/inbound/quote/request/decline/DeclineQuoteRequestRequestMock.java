package br.com.events.event.event.data.io.inbound.quote.request.decline;

import br.com.events.event.event.MockConstants;
import br.com.events.event.event.data.model.type.BusinessType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DeclineQuoteRequestRequestMock {

    public static DeclineQuoteRequestRequest build() {
        return new DeclineQuoteRequestRequest(MockConstants.STRING, BusinessType.BAND);
    }
}
