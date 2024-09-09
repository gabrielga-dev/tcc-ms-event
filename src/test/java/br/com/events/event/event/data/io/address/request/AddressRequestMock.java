package br.com.events.event.event.data.io.address.request;


import br.com.events.event.event.MockConstants;
import br.com.events.event.event.data.io.inbound.address.in.AddressRequest;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AddressRequestMock {

    public static AddressRequest build() {
        return AddressRequest
                .builder()
                .street(MockConstants.STRING)
                .number(MockConstants.INTEGER)
                .neighbour(MockConstants.STRING)
                .complement(MockConstants.STRING)
                .cityId(MockConstants.LONG)
                .stateIso(MockConstants.STRING)
                .countryIso(MockConstants.STRING)
                .zipCode(MockConstants.STRING)
                .build();
    }
}
