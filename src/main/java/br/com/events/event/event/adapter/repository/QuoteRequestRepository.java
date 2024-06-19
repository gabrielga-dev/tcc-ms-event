package br.com.events.event.event.adapter.repository;

import br.com.events.event.event.data.model.QuoteRequest;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.Optional;

public interface QuoteRequestRepository {

    List<QuoteRequest> findByEventUuid(String eventUuid);

    QuoteRequest save(QuoteRequest toSave);

    Optional<QuoteRequest> findById(String quoteRequestUuid);

    void delete(QuoteRequest quoteRequestUuid);

    List<QuoteRequest> findAll(Specification<QuoteRequest> specification);
}
