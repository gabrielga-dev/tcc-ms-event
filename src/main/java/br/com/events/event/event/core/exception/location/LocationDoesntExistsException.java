package br.com.events.event.event.core.exception.location;

import br.com.events.event.event.core.exception.bad_request.NotFoundException;

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
