package br.com.events.event.event.process.event.create_event.validations.caller;

import br.com.events.event.event.domain.io.inbound.event.create.in.CreateEventUseCaseForm;
import br.com.events.event.event.process.event.create_event.validations.CreateEventValidation;
import br.com.events.event.event.process.event.create_event.validations.CreateEventValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

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
    public Void callProcesses(final CreateEventUseCaseForm toValidate) {
        validations.forEach(validation -> validation.validate(toValidate));
        return null;
    }
}
