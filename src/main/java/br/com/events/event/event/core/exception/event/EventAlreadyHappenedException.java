package br.com.events.event.event.core.exception.event;

import br.com.events.event.event.core.exception.bad_request.BadRequestException;

public class EventAlreadyHappenedException extends BadRequestException {

    public EventAlreadyHappenedException() {
        super(
                "Impossível realizar ação!",
                "O evento já aconteceu."
        );
    }
}
