package br.com.events.event.event.clean.business.use_case.event.impl;

import br.com.events.event.event.clean.business.command.band.CheckEventCommand;
import br.com.events.event.event.clean.business.command.band.SaveEventCommand;
import br.com.events.event.event.clean.business.use_case.event.CreateEventUseCase;
import br.com.events.event.event.clean.data.io.inbound.event.in.EventRequest;
import br.com.events.event.event.clean.data.io.inbound.event.out.EventResponse;
import br.com.events.event.event.clean.data.model.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * This class implements {@link CreateEventUseCase} to create a new event
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class CreateEventUseCaseImpl implements CreateEventUseCase {

    private final CheckEventCommand checkEventCommand;
    private final SaveEventCommand saveEventCommand;


    @Override
    public EventResponse execute(final EventRequest form) {
        checkEventCommand.execute(form);

        var toSave = new Event(form);

        var saved = saveEventCommand.execute(toSave);

        return new EventResponse(saved);
    }
}
