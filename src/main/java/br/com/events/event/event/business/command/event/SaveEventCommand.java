package br.com.events.event.event.business.command.event;

import br.com.events.event.event.adapter.repository.EventRepository;
import br.com.events.event.event.data.model.Event;
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
