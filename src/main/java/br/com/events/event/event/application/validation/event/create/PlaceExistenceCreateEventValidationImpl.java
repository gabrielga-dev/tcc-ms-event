package br.com.events.event.event.application.validation.event.create;

import org.springframework.stereotype.Component;

import br.com.events.event.event.application.useCase.event.exception.LocationDoesntExistsException;
import br.com.events.event.event.domain.io.event.create.useCase.in.CreateEventUseCaseForm;
import br.com.events.event.event.infrastructure.service.LocationService;
import br.com.events.event.event.infrastructure.validation.event.create.CreateEventValidation;
import lombok.RequiredArgsConstructor;

/**
 * This class validates if the incoming event's date is on present or future
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class PlaceExistenceCreateEventValidationImpl implements CreateEventValidation {

    private final LocationService locationService;

    @Override
    public void validate(final CreateEventUseCaseForm form) {
        var toValidate = form.getAddress();
        locationService.getCityByStateAndCountryIso2(
            toValidate.getCountry(),
            toValidate.getState(),
            toValidate.getCity()
        ).orElseThrow(LocationDoesntExistsException::new);
    }
}
