package br.com.events.event.event.data.io.inbound.address.out;


import br.com.events.event.event.data.io.address.city.CityResponseMock;
import br.com.events.event.event.data.model.AddressMock;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AddressResponseMock {

    public static AddressResponse build() {
        return new AddressResponse(AddressMock.build(), CityResponseMock.build());
    }
}
