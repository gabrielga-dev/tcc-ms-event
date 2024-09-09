package br.com.events.event.event.business.use_case.quote.impl;

import br.com.events.event.event.MockConstants;
import br.com.events.event.event.business.command.business_type.band.FindBandCommand;
import br.com.events.event.event.business.command.document_template.FindDocumentTemplateCommand;
import br.com.events.event.event.business.command.document_template.GeneratePdfDocumentCommand;
import br.com.events.event.event.business.command.person.FindPersonCommand;
import br.com.events.event.event.business.command.quote_request.FindQuoteRequestCommand;
import br.com.events.event.event.business.service.AuthService;
import br.com.events.event.event.core.exception.document_template.CouldNotGenerateDocumentException;
import br.com.events.event.event.data.io.inbound.auth.AuthenticatedPersonMock;
import br.com.events.event.event.data.io.inbound.document_template.PdfConfigurationDTO;
import br.com.events.event.event.data.io.outbound.msAuth.person.findByUuid.out.PersonResponseMock;
import br.com.events.event.event.data.io.outbound.ms_band.band.BandProfileMsBandResponseMock;
import br.com.events.event.event.data.model.DocumentTemplateMock;
import br.com.events.event.event.data.model.QuoteRequestMock;
import br.com.events.event.event.data.model.type.BusinessType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyMap;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link GenerateQuoteContractUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class GenerateQuoteContractUseCaseImplTest {

    @InjectMocks
    private GenerateQuoteContractUseCaseImpl useCase;

    @Mock
    private AuthService authService;
    @Mock
    private FindQuoteRequestCommand findQuoteRequestCommand;
    @Mock
    private FindDocumentTemplateCommand findDocumentTemplateCommand;
    @Mock
    private FindBandCommand findBandCommand;
    @Mock
    private FindPersonCommand findPersonCommand;
    @Mock
    private GeneratePdfDocumentCommand generatePdfDocumentCommand;

    @Test
    @DisplayName("execute - when business type is not supported, then throws CouldNotGenerateDocumentException")
    void executeWhenBusinessTypeIsNotSupportedThenThrowsCouldNotGenerateDocumentException() {
        var quoteRequest = QuoteRequestMock.build();
        quoteRequest.setBusinessType(BusinessType.BUFFET);
        when(findQuoteRequestCommand.findByUuidOrThrow(anyString())).thenReturn(quoteRequest);

        Assertions.assertThrows(
                CouldNotGenerateDocumentException.class,
                () -> useCase.execute(MockConstants.STRING)
        );

        verify(findQuoteRequestCommand, times(1)).findByUuidOrThrow(anyString());
        verify(findDocumentTemplateCommand, times(0)).findByIdOrThrow(anyLong());
        verify(generatePdfDocumentCommand, times(0)).execute(any(PdfConfigurationDTO.class), anyMap());
        verify(findBandCommand, times(0)).execute(anyString());
        verify(findPersonCommand, times(0)).execute(anyString());
        verify(authService, times(0)).getAuthenticatedPerson();
    }

    @Test
    @DisplayName("execute - when business type is band, then generate document")
    void executeWhenBusinessTypeIsBandThenGenerateDocument() {
        var quoteRequest = QuoteRequestMock.build();
        quoteRequest.setBusinessType(BusinessType.BAND);
        when(findQuoteRequestCommand.findByUuidOrThrow(anyString())).thenReturn(quoteRequest);

        var template = DocumentTemplateMock.build();
        when(findDocumentTemplateCommand.findByIdOrThrow(anyLong())).thenReturn(template);

        var band = BandProfileMsBandResponseMock.build();
        when(findBandCommand.execute(anyString())).thenReturn(band);

        var person = PersonResponseMock.build();
        when(findPersonCommand.execute(anyString())).thenReturn(person);

        var authenticatedPerson = AuthenticatedPersonMock.build();
        when(authService.getAuthenticatedPerson()).thenReturn(authenticatedPerson);

        when(generatePdfDocumentCommand.execute(any(PdfConfigurationDTO.class), anyMap())).thenReturn(new byte[10]);

        var returned = useCase.execute(MockConstants.STRING);

        Assertions.assertNotNull(returned);

        verify(findQuoteRequestCommand, times(1)).findByUuidOrThrow(anyString());
        verify(findDocumentTemplateCommand, times(1)).findByIdOrThrow(anyLong());
        verify(generatePdfDocumentCommand, times(1)).execute(any(PdfConfigurationDTO.class), anyMap());
        verify(findBandCommand, times(1)).execute(anyString());
        verify(findPersonCommand, times(2)).execute(anyString());
        verify(authService, times(1)).getAuthenticatedPerson();
    }
}