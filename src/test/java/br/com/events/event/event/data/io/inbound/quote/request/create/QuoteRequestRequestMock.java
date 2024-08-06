package br.com.events.event.event.data.io.inbound.quote.request.create;

import br.com.events.event.event.MockConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class QuoteRequestRequestMock {

    public static QuoteRequestRequest build() {
        return new QuoteRequestRequest(
                MockConstants.STRING,
                new ArrayList<>(List.of(MusicQuoteRequestRequestMock.build())),
                new ArrayList<>(List.of(MusicianTypeQuoteRequestRequestMock.build()))
        );
    }
}
