package br.com.events.event.event.business.use_case.event.impl;

import br.com.events.event.event.MockConstants;
import br.com.events.event.event.business.command.event.FindEventCommand;
import br.com.events.event.event.business.command.event.SaveEventCommand;
import br.com.events.event.event.business.service.AuthService;
import br.com.events.event.event.core.exception.event.EventAlreadyHappenedException;
import br.com.events.event.event.core.exception.event.EventDoesNotExistsException;
import br.com.events.event.event.core.exception.event.NotEventOwnerException;
import br.com.events.event.event.data.io.inbound.auth.AuthenticatedPersonMock;
import br.com.events.event.event.data.model.Event;
import br.com.events.event.event.data.model.EventMock;
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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link CancelEventUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class CancelEventUseCaseImplTest {

    @InjectMocks
    private CancelEventUseCaseImpl useCase;

    @Mock
    private AuthService authService;
    @Mock
    private FindEventCommand findEventCommand;
    @Mock
    private SaveEventCommand saveEventCommand;

    @Test
    @DisplayName("execute - when event does note exist, then throw EventDoesNotExistsException")
    void executeWhenEventDoesNotExistThenThrowEventDoesNotExistsException() {
        when(findEventCommand.byUuid(anyString())).thenReturn(Optional.empty());

        Assertions.assertThrows(
                EventDoesNotExistsException.class,
                () -> useCase.execute(MockConstants.STRING)
        );

        verify(findEventCommand, times(1)).byUuid(anyString());
        verify(saveEventCommand, times(0)).execute(any(Event.class));
        verify(authService, times(0)).getAuthenticatedPerson();
    }

    @Test
    @DisplayName("execute - when event already happened, then throw EventAlreadyHappenedException")
    void executeWhenEventAlreadyHappenedThenThrowEventAlreadyHappenedException() {
        var event = EventMock.build();
        event.setDate(LocalDateTime.now().minusYears(1L));

        when(findEventCommand.byUuid(anyString())).thenReturn(Optional.of(event));

        Assertions.assertThrows(
                EventAlreadyHappenedException.class,
                () -> useCase.execute(MockConstants.STRING)
        );

        verify(findEventCommand, times(1)).byUuid(anyString());
        verify(saveEventCommand, times(0)).execute(any(Event.class));
        verify(authService, times(0)).getAuthenticatedPerson();
    }

    @Test
    @DisplayName("execute - when person is not event owner, then throw NotEventOwnerException")
    void executeWhenPersonIsNotEventOwnerThenThrowNotEventOwnerException() {
        var event = EventMock.build();
        event.setDate(LocalDateTime.now().plusYears(1L));
        when(findEventCommand.byUuid(anyString())).thenReturn(Optional.of(event));

        var person = AuthenticatedPersonMock.build();
        person.setUuid(MockConstants.STRING.repeat(2));
        when(authService.getAuthenticatedPerson()).thenReturn(person);

        Assertions.assertThrows(
                NotEventOwnerException.class,
                () -> useCase.execute(MockConstants.STRING)
        );

        verify(findEventCommand, times(1)).byUuid(anyString());
        verify(saveEventCommand, times(0)).execute(any(Event.class));
        verify(authService, times(1)).getAuthenticatedPerson();
    }

    @Test
    @DisplayName("execute - when person is event owner, then cancel event")
    void executeWhenPersonIsEventOwnerThenCancelEvent() {
        var event = EventMock.build();
        event.setDate(LocalDateTime.now().plusYears(1L));
        event.setActive(Boolean.TRUE);
        event.setUpdateDate(null);
        when(findEventCommand.byUuid(anyString())).thenReturn(Optional.of(event));

        var person = AuthenticatedPersonMock.build();
        when(authService.getAuthenticatedPerson()).thenReturn(person);

        when(saveEventCommand.execute(any(Event.class))).thenReturn(event);

        Assertions.assertDoesNotThrow(() -> useCase.execute(MockConstants.STRING));

        Assertions.assertFalse(event.getActive());
        Assertions.assertNotNull(event.getUpdateDate());

        verify(findEventCommand, times(1)).byUuid(anyString());
        verify(saveEventCommand, times(1)).execute(any(Event.class));
        verify(authService, times(1)).getAuthenticatedPerson();
    }
}