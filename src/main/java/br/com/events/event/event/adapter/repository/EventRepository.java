package br.com.events.event.event.adapter.repository;

import br.com.events.event.event.data.model.Event;

public interface EventRepository {

    Event save(Event toSave);
}
