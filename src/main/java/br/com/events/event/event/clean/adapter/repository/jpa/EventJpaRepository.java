package br.com.events.event.event.clean.adapter.repository.jpa;

import br.com.events.event.event.clean.adapter.repository.EventRepository;
import br.com.events.event.event.clean.data.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * This interface make the communication with the database event's table
 *
 * @author Gabriel Guimarães de Almeida
 */
@Repository
public interface EventJpaRepository extends EventRepository, JpaRepository<Event, String> {

}
