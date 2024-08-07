package br.com.events.event.event.business.use_case.quote.impl;

import br.com.events.event.event.business.command.business_type.band.FindBandCommand;
import br.com.events.event.event.business.command.document_template.FindDocumentTemplateCommand;
import br.com.events.event.event.business.command.document_template.GeneratePdfDocumentCommand;
import br.com.events.event.event.business.command.person.FindPersonCommand;
import br.com.events.event.event.business.command.quote_request.FindQuoteRequestCommand;
import br.com.events.event.event.business.service.AuthService;
import br.com.events.event.event.business.use_case.quote.GenerateQuoteContractUseCase;
import br.com.events.event.event.core.exception.document_template.CouldNotGenerateDocumentException;
import br.com.events.event.event.core.util.BigDecimalUtil;
import br.com.events.event.event.core.util.DateUtil;
import br.com.events.event.event.core.util.FileUtil;
import br.com.events.event.event.data.io.inbound.document_template.DocumentTemplateType;
import br.com.events.event.event.data.io.inbound.document_template.PdfConfigurationDTO;
import br.com.events.event.event.data.io.inbound.document_template.PdfDTO;
import br.com.events.event.event.data.model.QuoteRequest;
import br.com.events.event.event.data.model.type.BusinessType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class GenerateQuoteContractUseCaseImpl implements GenerateQuoteContractUseCase {

    private final AuthService authService;
    private final FindQuoteRequestCommand findQuoteRequestCommand;
    private final FindDocumentTemplateCommand findDocumentTemplateCommand;
    private final FindBandCommand findBandCommand;
    private final FindPersonCommand findPersonCommand;
    private final GeneratePdfDocumentCommand generatePdfDocumentCommand;

    @Override
    public PdfDTO execute(String quoteRequestUuid) {
        var quoteRequest = findQuoteRequestCommand.findByUuidOrThrow(quoteRequestUuid);

        if (Objects.requireNonNull(quoteRequest.getBusinessType()) == BusinessType.BAND) {
            return this.generateForBand(quoteRequest);
        }
        throw new CouldNotGenerateDocumentException();
    }

    private PdfDTO generateForBand(QuoteRequest quoteRequest) {
        var template = findDocumentTemplateCommand.findByIdOrThrow(DocumentTemplateType.BAND_CONTRACT.getId());
        var configuration = new PdfConfigurationDTO(template.getContent());
        var params = this.generateParams(quoteRequest);

        var fileName = String.format(
                "contrato_para_eveto_%s.pdf",
                FileUtil.toSnakeCase(quoteRequest.getEvent().getName())
        );

        return new PdfDTO(fileName, generatePdfDocumentCommand.execute(configuration, params));
    }

    private Map<String, Object> generateParams(QuoteRequest quoteRequest) {
        var band = findBandCommand.execute(quoteRequest.getBusinessUuid());
        var event = quoteRequest.getEvent();
        var bandOwner = findPersonCommand.execute(band.getOwnerUuid());
        var contractor = findPersonCommand.execute(authService.getAuthenticatedPerson().getUuid());

        var params = new HashMap<String, Object>();

        params.put("bandName", band.getName());
        params.put("contractorName", contractor.getCompleteName());
        params.put("contractorCpf", contractor.getCpf());
        params.put("contractorEmail", contractor.getEmail());

        params.put("bandOwnerName", bandOwner.getCompleteName());
        params.put("bandOwnerCpf", bandOwner.getCpf());
        params.put("bandOwnerEmail", bandOwner.getEmail());

        params.put("eventName", event.getName());
        params.put("eventTime", DateUtil.formatTime(event.getDate().toLocalTime()));
        params.put("eventInFull", DateUtil.dateInFull(event.getDate()));
        params.put("eventAddress", event.getAddress().toString());

        params.put("quotePrice", BigDecimalUtil.format(quoteRequest.getPrice()));

        return params;
    }
}
