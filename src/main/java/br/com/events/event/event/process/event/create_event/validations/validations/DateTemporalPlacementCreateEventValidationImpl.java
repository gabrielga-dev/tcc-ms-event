package br.com.events.event.event.process.event.create_event.validations.validations;

import br.com.events.event.event.domain.exception.event.create.DateOnPastCreateEventException;
import br.com.events.event.event.domain.io.inbound.event.create.in.CreateEventUseCaseForm;
import br.com.events.event.event.process.event.create_event.validations.CreateEventValidation;
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
    public Void process(final CreateEventUseCaseForm toValidate) {
        var dateCopy = toValidate.getDate().plusDays(0);
        var now = LocalDateTime.now();

        if (dateCopy.isBefore(now)){
            throw new DateOnPastCreateEventException();
        }

        return null;
    }
}
