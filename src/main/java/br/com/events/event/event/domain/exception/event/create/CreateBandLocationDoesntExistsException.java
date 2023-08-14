package br.com.events.event.event.domain.exception.event.create;

import br.com.events.event.event.domain.exception.bad_request.NotFoundException;

/**
 * This exception is thrown when, at event creation feature, the location is not found
 *
 * @author Gabriel Guimarães de Almeida
 */
public class CreateBandLocationDoesntExistsException extends NotFoundException {

    public CreateBandLocationDoesntExistsException() {
        super(
            "Endereço inválido!",
            "Por favor, selecione um endereço válido."
        );
    }
}
