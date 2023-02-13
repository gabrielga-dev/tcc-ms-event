package br.com.events.event.event.infrastructure.exception.badRequest;

import org.springframework.http.HttpStatus;

import br.com.events.event.event.infrastructure.exception.BusinessException;

/**
 * This exception will be extended by any other exception that needs to return a 400 http status
 *
 * @author Gabriel Guimarães de Almeida
 */
public class ForbiddenRequestException extends BusinessException {

    private static final long serialVersionUID = 1L;

    public ForbiddenRequestException(
        final String message, final String description
    ) {
        super(HttpStatus.FORBIDDEN, HttpStatus.FORBIDDEN.value(), message, description);
    }
}
