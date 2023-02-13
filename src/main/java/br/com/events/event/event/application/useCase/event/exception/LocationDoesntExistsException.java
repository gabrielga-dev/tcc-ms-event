package br.com.events.event.event.application.useCase.event.exception;

import br.com.events.event.event.infrastructure.exception.badRequest.NotFoundException;

/**
 * This exception is thrown when, at event creation feature, the location is not found
 *
 * @author Gabriel Guimarães de Almeida
 */
public class LocationDoesntExistsException extends NotFoundException {

    public LocationDoesntExistsException() {
        super(
            "Endereço inválido!",
            "Por favor, selecione um endereço válido."
        );
    }
}
