package br.com.events.event.event.data.io.outbound.ms_band.message.quote_request;

import br.com.events.event.event.MockConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class QuoteRequestCreationErrorMessageMock {

    public static QuoteRequestCreationErrorMessage build() {
        return new QuoteRequestCreationErrorMessage(MockConstants.STRING);
    }

}