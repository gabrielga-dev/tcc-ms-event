package br.com.events.event.event.core.exception.event;

import br.com.events.event.event.core.exception.bad_request.BadRequestException;

/**
 * This exception is thrown when, creating a new event, the date is on the past
 *
 * @author Gabriel Guimarães de Almeida
 */
public class DateOnPastException extends BadRequestException {

    public DateOnPastException() {
        super(
            "Data inserida é inválida!",
            "Por favor, escolha uma data igual ou posterior da atual"
        );
    }
}
