package br.com.events.event.event.core.exception.internal_server_error;

import br.com.events.event.event.core.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class ServerSideException extends BusinessException {

    public ServerSideException(String message, String description) {
        super(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), message, description);
    }
}
