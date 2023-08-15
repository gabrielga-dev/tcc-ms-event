package br.com.events.event.event.process.event.create_event.validations;

import br.com.events.event.event.domain.io.inbound.event.create.in.CreateEventUseCaseForm;
import br.com.events.event.event.process.BaseProcess;

/**
 * This class dictates which methods are needed to validate the incoming data at create event
 * feature
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface CreateEventValidation extends BaseProcess<CreateEventUseCaseForm, Void> {

    default void validate(CreateEventUseCaseForm form){
        this.process(form);
    }
}
