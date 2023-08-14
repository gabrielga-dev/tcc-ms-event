package br.com.events.event.event.process.event.create_event.validations;

import br.com.events.event.event.domain.io.inbound.event.create.in.CreateEventUseCaseForm;
import br.com.events.event.event.process.BaseProcessCaller;

/**
 * This class dictates which methods are needed to call every needed validation the incoming data at create event
 * feature
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface CreateEventValidator extends BaseProcessCaller<CreateEventUseCaseForm, Void> {

    default void validate(CreateEventUseCaseForm form){
        this.callProcesses(form);
    }
}
