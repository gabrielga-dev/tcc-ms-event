package br.com.events.event.event.adapter.repository;

import br.com.events.event.event.data.model.QuoteRequest;

import java.util.List;

public interface QuoteRequestRepository {

    List<QuoteRequest> findByEventUuid(String eventUuid);
}
