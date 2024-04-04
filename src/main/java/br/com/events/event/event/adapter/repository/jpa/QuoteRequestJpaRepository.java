package br.com.events.event.event.adapter.repository.jpa;

import br.com.events.event.event.adapter.repository.QuoteRequestRepository;
import br.com.events.event.event.data.model.QuoteRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuoteRequestJpaRepository extends QuoteRequestRepository, JpaRepository<QuoteRequest, String> {

    default List<QuoteRequest> findByEventUuid(String eventUuid) {
        return this.findByEvent_Uuid(eventUuid);
    }

    List<QuoteRequest> findByEvent_Uuid(String eventUuid);

}
