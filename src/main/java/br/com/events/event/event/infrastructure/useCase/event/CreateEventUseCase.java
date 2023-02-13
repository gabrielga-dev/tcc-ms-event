package br.com.events.event.event.infrastructure.useCase.event;

import br.com.events.event.event.domain.io.event.create.useCase.in.CreateEventUseCaseForm;
import br.com.events.event.event.domain.io.event.create.useCase.out.CreateEventUseCaseResult;
import br.com.events.event.event.infrastructure.useCase.UseCaseBase;

/**
 * This interface dictates all needed methods to create a new event
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface CreateEventUseCase extends UseCaseBase<CreateEventUseCaseForm, CreateEventUseCaseResult> {

}
