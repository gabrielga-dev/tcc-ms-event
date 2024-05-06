package br.com.events.event.event.core.exception.event;

import br.com.events.event.event.core.exception.bad_request.BadRequestException;
import br.com.events.event.event.core.exception.bad_request.NotFoundException;

/**
 * This exception is thrown when, creating a new event, the date is on the past
 *
 * @author Gabriel Guimarães de Almeida
 */
public class EventDoesNotExistsException extends NotFoundException {

    public EventDoesNotExistsException() {
        super(
            "Evento não encontrado!",
            "Por favor, insira um identificador de um evento cadastrado na plataforma."
        );
    }
}
