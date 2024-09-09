package br.com.events.event.event.data.io.inbound.quote.request.create;

import br.com.events.event.event.MockConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MusicQuoteRequestRequestMock {

    public static MusicQuoteRequestRequest build() {
        return new MusicQuoteRequestRequest(
                MockConstants.STRING,
                MockConstants.STRING,
                MockConstants.INTEGER
        );
    }
}
