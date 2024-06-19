package br.com.events.event.event.business.command.document_template.impl;

import br.com.events.event.event.adapter.repository.DocumentTemplateRepository;
import br.com.events.event.event.business.command.document_template.FindDocumentTemplateCommand;
import br.com.events.event.event.core.exception.document_template.DocumentTemplateNotFoundException;
import br.com.events.event.event.data.model.DocumentTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindDocumentTemplateCommandImpl implements FindDocumentTemplateCommand {

    private final DocumentTemplateRepository documentTemplateRepository;

    @Override
    public DocumentTemplate findByIdOrThrow(Long id) {
        return documentTemplateRepository.findById(id).orElseThrow(DocumentTemplateNotFoundException::new);
    }
}
