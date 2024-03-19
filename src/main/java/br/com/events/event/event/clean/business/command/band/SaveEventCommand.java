package br.com.events.event.event.clean.business.command.band;

import br.com.events.event.event.clean.adapter.repository.EventRepository;
import br.com.events.event.event.clean.data.model.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveEventCommand {

    private final EventRepository eventRepository;

    public Event execute(Event event) {
        return eventRepository.save(event);
    }
}
