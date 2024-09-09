package br.com.events.event.event.business.use_case.quote_request.impl;

import br.com.events.event.event.MockConstants;
import br.com.events.event.event.business.command.event.FindEventCommand;
import br.com.events.event.event.business.command.quote_request.FindQuoteRequestCommand;
import br.com.events.event.event.business.service.AuthService;
import br.com.events.event.event.core.exception.event.EventDoesNotExistsException;
import br.com.events.event.event.core.exception.event.NotEventOwnerException;
import br.com.events.event.event.data.io.inbound.auth.AuthenticatedPersonMock;
import br.com.events.event.event.data.model.EventMock;
import br.com.events.event.event.data.model.QuoteRequestMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link FindAllQuoteRequestUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class FindAllQuoteRequestUseCaseImplTest {

    @InjectMocks
    private FindAllQuoteRequestUseCaseImpl useCase;

    @Mock
    private AuthService authService;
    @Mock
    private FindEventCommand findEventCommand;
    @Mock
    private FindQuoteRequestCommand findQuoteRequestCommand;

    @Test
    @DisplayName("execute - when event is not found, then throw EventDoesNotExistsException")
    void executeWhenEventIsNotFoundThenThrowEventDoesNotExistsException() {
        when(findEventCommand.byUuid(anyString())).thenReturn(Optional.empty());

        Assertions.assertThrows(
                EventDoesNotExistsException.class,
                () -> useCase.execute(MockConstants.STRING)
        );

        verify(findEventCommand, times(1)).byUuid(anyString());
        verify(authService, times(0)).getAuthenticatedPerson();
        verify(findQuoteRequestCommand, times(0)).findByUuid(anyString());
    }

    @Test
    @DisplayName("execute - when user is not event owner, then throw NotEventOwnerException")
    void executeWhenUserIsNotEventOwnerThenThrowNotEventOwnerException() {
        var event = EventMock.build();
        when(findEventCommand.byUuid(anyString())).thenReturn(Optional.of(event));

        var person = AuthenticatedPersonMock.build();
        person.setUuid(MockConstants.STRING.repeat(2));
        when(authService.getAuthenticatedPerson()).thenReturn(person);

        Assertions.assertThrows(
                NotEventOwnerException.class,
                () -> useCase.execute(MockConstants.STRING)
        );

        verify(findEventCommand, times(1)).byUuid(anyString());
        verify(authService, times(1)).getAuthenticatedPerson();
        verify(findQuoteRequestCommand, times(0)).findByUuid(anyString());
    }

    @Test
    @DisplayName("execute - when everything is ok, then list of quote requests")
    void executeWhenEverythingIsOkThenListOfQuoteRequests() {
        var event = EventMock.build();
        when(findEventCommand.byUuid(anyString())).thenReturn(Optional.of(event));

        var person = AuthenticatedPersonMock.build();
        when(authService.getAuthenticatedPerson()).thenReturn(person);

        var quoteRequest = QuoteRequestMock.build();
        when(findQuoteRequestCommand.findAll(anyString())).thenReturn(List.of(quoteRequest));

        var returned = useCase.execute(MockConstants.STRING);

        Assertions.assertNotNull(returned);
        Assertions.assertFalse(returned.isEmpty());
        Assertions.assertEquals(2, returned.size());

        verify(findEventCommand, times(1)).byUuid(anyString());
        verify(authService, times(1)).getAuthenticatedPerson();
        verify(findQuoteRequestCommand, times(1)).findAll(anyString());
    }
}