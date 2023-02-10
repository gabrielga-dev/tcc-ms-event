package br.com.events.event.event.application.validation.event.create;

import java.util.List;

import org.springframework.stereotype.Component;

import br.com.events.event.event.domain.io.event.create.useCase.in.CreateEventUseCaseForm;
import br.com.events.event.event.infrastructure.validation.event.create.CreateEventValidation;
import br.com.events.event.event.infrastructure.validation.event.create.CreateEventValidator;
import lombok.RequiredArgsConstructor;

/**
 * This class calls every needed validation to the incoming data at create event feature
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class CreateEventValidatorImpl implements CreateEventValidator {

    private final List<CreateEventValidation> validations;

    @Override
    public void validate(final CreateEventUseCaseForm toValidate) {
        validations.forEach(
            validation -> validation.validate(toValidate)
        );
    }
}
