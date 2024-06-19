package br.com.events.event.event.adapter.repository.jpa;

import br.com.events.event.event.adapter.repository.EventRepository;
import br.com.events.event.event.data.model.Event;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;


/**
 * This interface make the communication with the database event's table
 *
 * @author Gabriel Guimarães de Almeida
 */
@Repository
public interface EventJpaRepository extends EventRepository, JpaRepository<Event, String> {

    @Query(
            "SELECT event FROM Event event WHERE " +
                    "(event.active = true) AND " +
                    "((:ownerUuid IS NULL) OR (event.ownerUuid = :ownerUuid)) AND " +
                    "((:concluded IS NULL) OR ((:concluded = true) AND (CURRENT_DATE > event.date)) OR ((:concluded = false) AND (CURRENT_DATE <= event.date))) AND " +
                    "((:startDate IS NULL) OR (:startDate <= event.date)) AND " +
                    "((:finalDate IS NULL) OR (:finalDate >= event.date)) AND " +
                    "((:name IS NULL) OR (event.name LIKE CONCAT('%',:name,'%')))"
    )
    Page<Event> findByCriteria(
            @Param("name") String name,
            @Param("startDate") LocalDateTime startDate,
            @Param("finalDate") LocalDateTime finalDate,
            @Param("concluded") Boolean concluded,
            @Param("ownerUuid") String ownerUuid,
            Pageable pageable
    );

    @Query("SELECT event FROM Event event WHERE event.uuid IN :uuids")
    List<Event> findByUuids(@Param("uuids") List<String> uuids);

    List<Event> findByOwnerUuid(String ownerUuid);
}
