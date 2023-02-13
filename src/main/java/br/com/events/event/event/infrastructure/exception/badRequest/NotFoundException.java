package br.com.events.event.event.infrastructure.exception.badRequest;

import org.springframework.http.HttpStatus;

import br.com.events.event.event.infrastructure.exception.BusinessException;

/**
 * This exception will be extended by any other exception that needs to return a 404 http status
 *
 * @author Gabriel Guimarães de Almeida
 */
public class NotFoundException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public NotFoundException(
        final String message, final String description
    ) {
        super(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), message, description);
    }
}
