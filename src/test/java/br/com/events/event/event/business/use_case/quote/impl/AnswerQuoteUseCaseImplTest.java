package br.com.events.event.event.business.use_case.quote.impl;

import br.com.events.event.event.MockConstants;
import br.com.events.event.event.business.command.quote.SendQuoteEventAnswerMessageCommand;
import br.com.events.event.event.business.command.quote_request.FindQuoteRequestCommand;
import br.com.events.event.event.business.command.quote_request.SaveQuoteRequestCommand;
import br.com.events.event.event.business.service.AuthService;
import br.com.events.event.event.core.exception.event.EventAlreadyHappenedException;
import br.com.events.event.event.core.exception.event.EventDoesNotExistsException;
import br.com.events.event.event.core.exception.event.NotEventOwnerException;
import br.com.events.event.event.core.exception.quote_request.QuoteRequestAlreadyAnsweredException;
import br.com.events.event.event.core.exception.quote_request.QuoteRequestDoesNotExistsException;
import br.com.events.event.event.data.io.inbound.auth.AuthenticatedPersonMock;
import br.com.events.event.event.data.model.QuoteRequest;
import br.com.events.event.event.data.model.QuoteRequestMock;
import br.com.events.event.event.data.model.type.QuoteRequestStatusType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link AnswerQuoteUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class AnswerQuoteUseCaseImplTest {

    @InjectMocks
    private AnswerQuoteUseCaseImpl useCase;

    @Mock
    private AuthService authService;
    @Mock
    private FindQuoteRequestCommand findQuoteRequestCommand;
    @Mock
    private SaveQuoteRequestCommand saveQuoteRequestCommand;
    @Mock
    private SendQuoteEventAnswerMessageCommand sendQuoteEventAnswerMessageCommand;

    @Test
    @DisplayName("execute - when quote request is not found, then throw QuoteRequestDoesNotExistsException")
    void executeWhenQuoteRequestIsNotFoundThenThrowQuoteRequestDoesNotExistsException() {
        when(findQuoteRequestCommand.findByUuid(anyString())).thenReturn(Optional.empty());

        Assertions.assertThrows(
                QuoteRequestDoesNotExistsException.class,
                () -> useCase.execute(MockConstants.STRING, MockConstants.BOOLEAN)
        );

        verify(findQuoteRequestCommand, times(1)).findByUuid(anyString());
        verify(authService, times(0)).getAuthenticatedPerson();
        verify(saveQuoteRequestCommand, times(0)).execute(any(QuoteRequest.class));
        verify(sendQuoteEventAnswerMessageCommand, times(0)).execute(any(QuoteRequest.class), anyBoolean());
    }

    @Test
    @DisplayName("execute - when person is not event owner, then throw NotEventOwnerException")
    void executeWhenPersonIsNotEventOwnerThenThrowNotEventOwnerException() {
        var quoteRequest = QuoteRequestMock.build();
        when(findQuoteRequestCommand.findByUuid(anyString())).thenReturn(Optional.of(quoteRequest));

        var person = AuthenticatedPersonMock.build();
        person.setUuid(MockConstants.STRING.repeat(2));
        when(authService.getAuthenticatedPerson()).thenReturn(person);

        Assertions.assertThrows(
                NotEventOwnerException.class,
                () -> useCase.execute(MockConstants.STRING, MockConstants.BOOLEAN)
        );

        verify(findQuoteRequestCommand, times(1)).findByUuid(anyString());
        verify(authService, times(1)).getAuthenticatedPerson();
        verify(saveQuoteRequestCommand, times(0)).execute(any(QuoteRequest.class));
        verify(sendQuoteEventAnswerMessageCommand, times(0)).execute(any(QuoteRequest.class), anyBoolean());
    }

    @Test
    @DisplayName("execute - when quote request is not already answered, then throw QuoteRequestAlreadyAnsweredException")
    void executeWhenQuoteRequestIsNotAlreadyAnsweredThenThrowQuoteRequestAlreadyAnsweredException() {
        var quoteRequest = QuoteRequestMock.build();
        when(findQuoteRequestCommand.findByUuid(anyString())).thenReturn(Optional.of(quoteRequest));

        var person = AuthenticatedPersonMock.build();
        when(authService.getAuthenticatedPerson()).thenReturn(person);

        Assertions.assertThrows(
                QuoteRequestAlreadyAnsweredException.class,
                () -> useCase.execute(MockConstants.STRING, MockConstants.BOOLEAN)
        );

        verify(findQuoteRequestCommand, times(1)).findByUuid(anyString());
        verify(authService, times(1)).getAuthenticatedPerson();
        verify(saveQuoteRequestCommand, times(0)).execute(any(QuoteRequest.class));
        verify(sendQuoteEventAnswerMessageCommand, times(0)).execute(any(QuoteRequest.class), anyBoolean());
    }

    @Test
    @DisplayName("execute - when event is not active, then throw EventDoesNotExistsException")
    void executeWhenEventIsNotActiveThenThrowEventDoesNotExistsException() {
        var quoteRequest = QuoteRequestMock.build();
        quoteRequest.setStatus(QuoteRequestStatusType.ANSWERED);
        quoteRequest.getEvent().setActive(Boolean.FALSE);
        when(findQuoteRequestCommand.findByUuid(anyString())).thenReturn(Optional.of(quoteRequest));

        var person = AuthenticatedPersonMock.build();
        when(authService.getAuthenticatedPerson()).thenReturn(person);

        Assertions.assertThrows(
                EventDoesNotExistsException.class,
                () -> useCase.execute(MockConstants.STRING, MockConstants.BOOLEAN)
        );

        verify(findQuoteRequestCommand, times(1)).findByUuid(anyString());
        verify(authService, times(1)).getAuthenticatedPerson();
        verify(saveQuoteRequestCommand, times(0)).execute(any(QuoteRequest.class));
        verify(sendQuoteEventAnswerMessageCommand, times(0)).execute(any(QuoteRequest.class), anyBoolean());
    }

    @Test
    @DisplayName("execute - when event already happened, then throw EventAlreadyHappenedException")
    void executeWhenEventAlreadyHappenedThenThrowEventAlreadyHappenedException() {
        var quoteRequest = QuoteRequestMock.build();
        quoteRequest.setStatus(QuoteRequestStatusType.ANSWERED);
        quoteRequest.getEvent().setDate(LocalDateTime.now().minusYears(1L));
        when(findQuoteRequestCommand.findByUuid(anyString())).thenReturn(Optional.of(quoteRequest));

        var person = AuthenticatedPersonMock.build();
        when(authService.getAuthenticatedPerson()).thenReturn(person);

        Assertions.assertThrows(
                EventAlreadyHappenedException.class,
                () -> useCase.execute(MockConstants.STRING, MockConstants.BOOLEAN)
        );

        verify(findQuoteRequestCommand, times(1)).findByUuid(anyString());
        verify(authService, times(1)).getAuthenticatedPerson();
        verify(saveQuoteRequestCommand, times(0)).execute(any(QuoteRequest.class));
        verify(sendQuoteEventAnswerMessageCommand, times(0)).execute(any(QuoteRequest.class), anyBoolean());
    }

    @Test
    @DisplayName("execute - when everything is ok, then save changes")
    void executeWhenEverythingIsOkThenSaveChanges() {
        var quoteRequest = QuoteRequestMock.build();
        quoteRequest.setStatus(QuoteRequestStatusType.ANSWERED);
        quoteRequest.getEvent().setDate(LocalDateTime.now().plusYears(1L));
        when(findQuoteRequestCommand.findByUuid(anyString())).thenReturn(Optional.of(quoteRequest));

        var person = AuthenticatedPersonMock.build();
        when(authService.getAuthenticatedPerson()).thenReturn(person);

        when(saveQuoteRequestCommand.execute(any(QuoteRequest.class))).thenReturn(quoteRequest);

        doNothing().when(sendQuoteEventAnswerMessageCommand).execute(any(QuoteRequest.class), anyBoolean());

        Assertions.assertDoesNotThrow(
                () -> useCase.execute(MockConstants.STRING, MockConstants.BOOLEAN)
        );

        verify(findQuoteRequestCommand, times(1)).findByUuid(anyString());
        verify(authService, times(1)).getAuthenticatedPerson();
        verify(saveQuoteRequestCommand, times(1)).execute(any(QuoteRequest.class));
        verify(sendQuoteEventAnswerMessageCommand, times(1)).execute(any(QuoteRequest.class), anyBoolean());
    }
}