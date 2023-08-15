package br.com.events.event.event.process.event.create_event.validations.validations;

import br.com.events.event.event.domain.exception.event.create.CreateBandLocationDoesntExistsException;
import br.com.events.event.event.domain.io.inbound.event.create.in.CreateEventUseCaseForm;
import br.com.events.event.event.infrastructure.feign.msLocation.LocationFeignClient;
import br.com.events.event.event.process.event.create_event.validations.CreateEventValidation;
import feign.FeignException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * This class validates if the incoming event's date is on present or future
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class PlaceExistenceCreateEventValidationImpl implements CreateEventValidation {

    private final LocationFeignClient locationFeignClient;

    @Override
    public Void process(final CreateEventUseCaseForm form) {
        try{
            var toValidate = form.getAddress();
            locationFeignClient.validateIfAddressExists(
                    toValidate.getCityId(),
                    toValidate.getStateIso(),
                    toValidate.getCountryIso()
            );
        } catch (FeignException fe){
            throw new CreateBandLocationDoesntExistsException();
        }
        return null;
    }
}
