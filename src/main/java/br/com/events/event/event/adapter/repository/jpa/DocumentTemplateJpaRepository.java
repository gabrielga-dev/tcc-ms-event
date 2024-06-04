package br.com.events.event.event.adapter.repository.jpa;

import br.com.events.event.event.adapter.repository.DocumentTemplateRepository;
import br.com.events.event.event.data.model.DocumentTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentTemplateJpaRepository extends DocumentTemplateRepository, JpaRepository<DocumentTemplate, Long> {
}
