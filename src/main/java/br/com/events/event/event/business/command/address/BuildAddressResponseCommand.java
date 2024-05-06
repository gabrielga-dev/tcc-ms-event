package br.com.events.event.event.business.command.address;

import br.com.events.event.event.adapter.feing.LocationFeign;
import br.com.events.event.event.data.io.inbound.address.out.AddressResponse;
import br.com.events.event.event.data.model.Address;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BuildAddressResponseCommand {

    private final LocationFeign locationFeign;

    public AddressResponse execute(Address address) {
        var city = locationFeign.getCityByIdAndStateAndCountryIso(
                address.getCountry(),
                address.getState(),
                address.getCity()
        );

        return new AddressResponse(address, city);
    }
}
