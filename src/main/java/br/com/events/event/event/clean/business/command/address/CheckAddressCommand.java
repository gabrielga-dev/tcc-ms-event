package br.com.events.event.event.clean.business.command.address;

import br.com.events.event.event.clean.adapter.feing.LocationFeign;
import br.com.events.event.event.clean.core.exception.location.LocationDoesntExistsException;
import br.com.events.event.event.clean.data.io.inbound.address.IAddress;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CheckAddressCommand {

    private final LocationFeign locationFeign;

    public void execute(IAddress address) {
        try {
            locationFeign.validateIfAddressExists(
                    address.getCityId(),
                    address.getStateIso(),
                    address.getCountryIso()
            );
        } catch (FeignException fe) {
            throw new LocationDoesntExistsException();
        }
    }
}
