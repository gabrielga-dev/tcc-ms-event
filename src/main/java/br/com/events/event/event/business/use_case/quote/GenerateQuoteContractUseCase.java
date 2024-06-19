package br.com.events.event.event.business.use_case.quote;

import br.com.events.event.event.data.io.inbound.document_template.PdfDTO;

public interface GenerateQuoteContractUseCase {

    PdfDTO execute(String quoteRequestUuid);
}
