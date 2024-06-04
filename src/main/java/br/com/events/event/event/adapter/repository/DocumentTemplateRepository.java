package br.com.events.event.event.adapter.repository;

import br.com.events.event.event.data.model.DocumentTemplate;

import java.util.Optional;

public interface DocumentTemplateRepository {

    Optional<DocumentTemplate> findById(Long id);
}
