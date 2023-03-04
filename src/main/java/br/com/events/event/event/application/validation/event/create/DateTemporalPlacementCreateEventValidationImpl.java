package br.com.events.event.event.application.validation.event.create;

import br.com.events.event.event.application.validation.event.create.exception.DateOnPastCreateEventException;
import br.com.events.event.event.domain.io.event.create.useCase.in.CreateEventUseCaseForm;
import br.com.events.event.event.infrastructure.validation.event.create.CreateEventValidation;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

/**
 * This class validates if the incoming event's date is on present or future
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
public class DateTemporalPlacementCreateEventValidationImpl implements CreateEventValidation {

    @Override
    public void validate(final CreateEventUseCaseForm toValidate) {
        var dateCopy = toValidate.getDate().plusDays(0);
        var now = LocalDateTime.now();

        if (dateCopy.isBefore(now)){
            throw new DateOnPastCreateEventException();
        }
    }
}
