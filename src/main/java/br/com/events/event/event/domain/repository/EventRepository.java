package br.com.events.event.event.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.events.event.event.domain.model.Event;


/**
 * This interface make the communication with the database event's table
 *
 * @author Gabriel Guimarães de Almeida
 */
@Repository
public interface EventRepository extends JpaRepository<Event, String> {

}
