package br.com.events.event.event.business.use_case.event;

import br.com.events.event.event.data.io.inbound.event.in.EventRequest;
import br.com.events.event.event.data.io.inbound.event.out.EventResponse;

/**
 * This interface dictates all needed methods to create a new event
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface CreateEventUseCase {

    EventResponse execute(EventRequest form);
}
