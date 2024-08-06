package br.com.events.event.event.data.io.inbound.quote.request.create;

import br.com.events.event.event.MockConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MusicianTypeQuoteRequestRequestMock {

    public static MusicianTypeQuoteRequestRequest build() {
        return new MusicianTypeQuoteRequestRequest(
                MockConstants.STRING,
                MockConstants.INTEGER,
                MockConstants.STRING
        );
    }
}
