package br.com.events.event.event.clean.adapter.repository;

import br.com.events.event.event.clean.data.model.Event;

public interface EventRepository {

    Event save(Event toSave);
}
