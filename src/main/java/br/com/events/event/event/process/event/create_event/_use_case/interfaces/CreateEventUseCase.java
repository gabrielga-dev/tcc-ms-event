package br.com.events.event.event.process.event.create_event._use_case.interfaces;

import br.com.events.event.event.domain.io.inbound.event.create.in.CreateEventUseCaseForm;
import br.com.events.event.event.domain.io.inbound.event.create.out.CreateEventUseCaseResult;

/**
 * This interface dictates all needed methods to create a new event
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface CreateEventUseCase {

    CreateEventUseCaseResult execute(CreateEventUseCaseForm form);
}
