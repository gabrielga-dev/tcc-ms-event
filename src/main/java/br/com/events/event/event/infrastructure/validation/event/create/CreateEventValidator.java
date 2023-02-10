package br.com.events.event.event.infrastructure.validation.event.create;

import br.com.events.event.event.domain.io.event.create.useCase.in.CreateEventUseCaseForm;
import br.com.events.event.event.infrastructure.validation.BaseValidator;

/**
 * This class dictates which methods are needed to call every needed validation the incoming data at create event
 * feature
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface CreateEventValidator extends BaseValidator<CreateEventUseCaseForm> {

}
