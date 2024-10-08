package br.com.events.event.event.business.command.event;

import br.com.events.event.event.adapter.repository.EventRepository;
import br.com.events.event.event.data.io.inbound.event.in.EventCriteria;
import br.com.events.event.event.data.model.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindEventCommand {

    private final EventRepository eventRepository;

    public Page<Event> byCriteria(EventCriteria criteria, Pageable pageable) {
        return eventRepository.findByCriteria(
                criteria.getName(),
                criteria.getStartDate(),
                criteria.getFinalDate(),
                criteria.getConcluded(),
                criteria.getOwnerUuid(),
                pageable
        );
    }

    public Optional<Event> byUuid(String uuid) {
        return eventRepository.findById(uuid);
    }

    public List<Event> byUuids(List<String> uuids) {
        return eventRepository.findByUuids(uuids);
    }

    public List<Event> findAll(String ownerUuid) {
        return eventRepository.findByOwnerUuid(ownerUuid);
    }
}
