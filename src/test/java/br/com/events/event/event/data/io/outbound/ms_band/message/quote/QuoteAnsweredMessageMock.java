package br.com.events.event.event.data.io.outbound.ms_band.message.quote;

import br.com.events.event.event.MockConstants;
import br.com.events.event.event.data.model.type.BusinessType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class QuoteAnsweredMessageMock {

    public static QuoteAnsweredMessage build() {
        return new QuoteAnsweredMessage(
                MockConstants.STRING,
                MockConstants.STRING,
                BusinessType.BAND,
                MockConstants.STRING,
                MockConstants.STRING,
                MockConstants.STRING,
                MockConstants.BIG_DECIMAL,
                MockConstants.STRING
        );
    }
}
