package br.com.events.event.event.business.command.document_template;

import br.com.events.event.event.data.io.inbound.document_template.PdfConfigurationDTO;

import java.util.Map;

public interface GeneratePdfDocumentCommand {

    byte[] execute(PdfConfigurationDTO configuration, Map<String, Object> params);
}
