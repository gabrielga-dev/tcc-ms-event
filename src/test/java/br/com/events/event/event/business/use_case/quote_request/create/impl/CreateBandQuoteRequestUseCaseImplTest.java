package br.com.events.event.event.business.use_case.quote_request.create.impl;

import br.com.events.event.event.MockConstants;
import br.com.events.event.event.business.command.event.FindEventCommand;
import br.com.events.event.event.business.command.quote_request.SaveQuoteRequestCommand;
import br.com.events.event.event.business.command.quote_request.message.create.SendQuoteRequestMessageCommand;
import br.com.events.event.event.business.command.quote_request.message.create.SendQuoteRequestMessageCommandFactory;
import br.com.events.event.event.business.service.AuthService;
import br.com.events.event.event.core.exception.event.BusinessTypeNotSupportedYetException;
import br.com.events.event.event.core.exception.event.EventDoesNotExistsException;
import br.com.events.event.event.core.exception.event.NotEventOwnerException;
import br.com.events.event.event.data.io.inbound.auth.AuthenticatedPersonMock;
import br.com.events.event.event.data.io.inbound.quote.request.create.QuoteRequestRequest;
import br.com.events.event.event.data.io.inbound.quote.request.create.QuoteRequestRequestMock;
import br.com.events.event.event.data.model.EventMock;
import br.com.events.event.event.data.model.QuoteRequest;
import br.com.events.event.event.data.model.type.BusinessType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link CreateBandQuoteRequestUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class CreateBandQuoteRequestUseCaseImplTest {

    @InjectMocks
    private CreateBandQuoteRequestUseCaseImpl useCase;

    @Mock
    private AuthService authService;
    @Mock
    private FindEventCommand findEventCommand;
    @Mock
    private SendQuoteRequestMessageCommandFactory sendQuoteRequestMessageCommandFactory;
    @Mock
    private SaveQuoteRequestCommand saveQuoteRequestCommand;

    @Test
    @DisplayName("getHandledBusinessType - when called return band value")
    void getHandledBusinessTypeWhenCalledReturnBandValue() {
        var returned = useCase.getHandledBusinessType();

        Assertions.assertEquals(BusinessType.BAND, returned);
    }

    @Test
    @DisplayName("execute - when event is not found, then throws EventDoesNotExistsException")
    void executeWhenEventIsNotFoundThenThrowEventDoesNotExistsException() {
        when(findEventCommand.byUuid(anyString())).thenReturn(Optional.empty());

        var request = QuoteRequestRequestMock.build();
        Assertions.assertThrows(
                EventDoesNotExistsException.class,
                () -> useCase.execute(MockConstants.STRING, MockConstants.STRING, request)
        );

        verify(findEventCommand, times(1)).byUuid(anyString());
        verify(authService, times(0)).getAuthenticatedPerson();
        verify(sendQuoteRequestMessageCommandFactory, times(0)).findMessageSender(any(BusinessType.class));
        verify(saveQuoteRequestCommand, times(0)).execute(any(QuoteRequest.class));
    }

    @Test
    @DisplayName("execute - when user is not event owner, then throws NotEventOwnerException")
    void executeWhenUserIsNotEventOwnerThenThrowNotEventOwnerException() {
        var event = EventMock.build();
        when(findEventCommand.byUuid(anyString())).thenReturn(Optional.of(event));

        var authenticatedPerson = AuthenticatedPersonMock.build();
        authenticatedPerson.setUuid(MockConstants.STRING.repeat(2));
        when(authService.getAuthenticatedPerson()).thenReturn(authenticatedPerson);

        var request = QuoteRequestRequestMock.build();
        Assertions.assertThrows(
                NotEventOwnerException.class,
                () -> useCase.execute(MockConstants.STRING, MockConstants.STRING, request)
        );

        verify(findEventCommand, times(1)).byUuid(anyString());
        verify(authService, times(1)).getAuthenticatedPerson();
        verify(sendQuoteRequestMessageCommandFactory, times(0)).findMessageSender(any(BusinessType.class));
        verify(saveQuoteRequestCommand, times(0)).execute(any(QuoteRequest.class));
    }

    @Test
    @DisplayName("execute - when messenger is not found, then throws BusinessTypeNotSupportedYetException")
    void executeWhenMessengerIsNotFoundThenThrowBusinessTypeNotSupportedYetException() {
        var event = EventMock.build();
        when(findEventCommand.byUuid(anyString())).thenReturn(Optional.of(event));

        var authenticatedPerson = AuthenticatedPersonMock.build();
        when(authService.getAuthenticatedPerson()).thenReturn(authenticatedPerson);

        when(sendQuoteRequestMessageCommandFactory.findMessageSender(any(BusinessType.class)))
                .thenReturn(Optional.empty());

        var request = QuoteRequestRequestMock.build();
        Assertions.assertThrows(
                BusinessTypeNotSupportedYetException.class,
                () -> useCase.execute(MockConstants.STRING, MockConstants.STRING, request)
        );

        verify(findEventCommand, times(1)).byUuid(anyString());
        verify(authService, times(1)).getAuthenticatedPerson();
        verify(sendQuoteRequestMessageCommandFactory, times(1)).findMessageSender(any(BusinessType.class));
        verify(saveQuoteRequestCommand, times(0)).execute(any(QuoteRequest.class));
    }

    @Test
    @DisplayName("execute - when everything is ok, then save updated quote request")
    void executeWhenEverythingIsOkThenSaveUpdatedQuoteRequest() {
        var event = EventMock.build();
        when(findEventCommand.byUuid(anyString())).thenReturn(Optional.of(event));

        var authenticatedPerson = AuthenticatedPersonMock.build();
        when(authService.getAuthenticatedPerson()).thenReturn(authenticatedPerson);

        var mockedMessageSender = mock(SendQuoteRequestMessageCommand.class);
        doNothing().when(mockedMessageSender)
                .sendMessage(anyString(), anyString(), anyString(), any(QuoteRequestRequest.class));
        when(sendQuoteRequestMessageCommandFactory.findMessageSender(any(BusinessType.class)))
                .thenReturn(Optional.of(mockedMessageSender));

        var request = QuoteRequestRequestMock.build();
        Assertions.assertDoesNotThrow(
                () -> useCase.execute(MockConstants.STRING, MockConstants.STRING, request)
        );

        verify(findEventCommand, times(1)).byUuid(anyString());
        verify(authService, times(1)).getAuthenticatedPerson();
        verify(sendQuoteRequestMessageCommandFactory, times(1)).findMessageSender(any(BusinessType.class));
        verify(mockedMessageSender, times(1)).sendMessage(
                anyString(), anyString(), anyString(), any(QuoteRequestRequest.class)
        );
        verify(saveQuoteRequestCommand, times(1)).execute(any(QuoteRequest.class));
    }
}