package br.com.events.event.event.infrastructure.validation.event.create;

import br.com.events.event.event.domain.io.event.create.useCase.in.CreateEventUseCaseForm;
import br.com.events.event.event.infrastructure.validation.BaseValidation;

/**
 * This class dictates which methods are needed to validate the incoming data at create event
 * feature
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface CreateEventValidation extends BaseValidation<CreateEventUseCaseForm> {

}
