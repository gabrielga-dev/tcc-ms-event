package br.com.events.event.event.domain.exception.event.create;

import br.com.events.event.event.domain.exception.bad_request.BadRequestException;

/**
 * This exception is thrown when, creating a new event, the date is on the past
 *
 * @author Gabriel Guimarães de Almeida
 */
public class DateOnPastCreateEventException extends BadRequestException {

    public DateOnPastCreateEventException() {
        super(
            "Data inserida é inválida!",
            "Por favor, escolha uma data igual ou posterior da atual"
        );
    }
}
