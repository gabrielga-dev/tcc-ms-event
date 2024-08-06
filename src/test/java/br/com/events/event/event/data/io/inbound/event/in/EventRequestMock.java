package br.com.events.event.event.data.io.inbound.event.in;

import br.com.events.event.event.MockConstants;
import br.com.events.event.event.data.io.address.request.AddressRequestMock;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EventRequestMock {

    public static EventRequest build() {
        return new EventRequest(
                MockConstants.STRING,
                MockConstants.STRING,
                AddressRequestMock.build(),
                MockConstants.LONG
        );
    }
}
