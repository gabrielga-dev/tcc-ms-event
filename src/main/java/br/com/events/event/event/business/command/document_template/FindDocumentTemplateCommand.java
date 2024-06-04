package br.com.events.event.event.business.command.document_template;

import br.com.events.event.event.data.model.DocumentTemplate;

public interface FindDocumentTemplateCommand {

    DocumentTemplate findByIdOrThrow(Long id);
}
