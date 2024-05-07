package br.com.events.event.event.adapter.repository;

import br.com.events.event.event.data.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface EventRepository {

    Event save(Event toSave);

    Page<Event> findByCriteria(
            String name,
            LocalDateTime startDate,
            LocalDateTime finalDate,
            Boolean concluded,
            String ownerUuid,
            Pageable pageable
    );

    Optional<Event> findById(String uuid);

    List<Event> findByUuids(List<String> uuids);
}
