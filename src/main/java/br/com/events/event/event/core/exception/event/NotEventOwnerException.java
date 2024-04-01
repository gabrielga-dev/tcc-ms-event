package br.com.events.event.event.core.exception.event;

import br.com.events.event.event.core.exception.bad_request.UnauthorizedRequestException;

public class NotEventOwnerException extends UnauthorizedRequestException {

    public NotEventOwnerException() {
        super(
                "Impossível realizar ação!",
                "Você não é o responsável por este evento, portanto, você não pode realizar essa ação."
        );
    }
}
