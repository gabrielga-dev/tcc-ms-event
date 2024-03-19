package br.com.events.event.event.core.exception.interceptors;

import br.com.events.event.event.core.exception.bad_request.UnauthorizedRequestException;

/**
 * This exception is called when a request with no api-ey is received
 *
 * @author Gabriel Guimarães de Almeida
 */
public class NoTokenReceivedException extends UnauthorizedRequestException {

    public NoTokenReceivedException() {
        super(
            "Usuário nao autenticado!",
            "Por favor, se autentique antes de tentar realizar esta ação!"
        );
    }
}
