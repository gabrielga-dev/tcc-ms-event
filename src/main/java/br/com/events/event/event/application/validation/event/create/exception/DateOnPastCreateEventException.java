package br.com.events.event.event.application.validation.event.create.exception;

import br.com.events.event.event.infrastructure.exception.badRequest.BadRequestException;

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
