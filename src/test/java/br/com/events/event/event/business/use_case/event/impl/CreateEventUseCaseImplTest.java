package br.com.events.event.event.business.use_case.event.impl;

import br.com.events.event.event.business.command.event.CheckEventCommand;
import br.com.events.event.event.business.command.event.SaveEventCommand;
import br.com.events.event.event.business.service.AuthService;
import br.com.events.event.event.data.io.inbound.auth.AuthenticatedPersonMock;
import br.com.events.event.event.data.io.inbound.event.in.EventRequest;
import br.com.events.event.event.data.io.inbound.event.in.EventRequestMock;
import br.com.events.event.event.data.model.Event;
import br.com.events.event.event.data.model.EventMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link CreateEventUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class CreateEventUseCaseImplTest {

    @InjectMocks
    private CreateEventUseCaseImpl useCase;

    @Mock
    private AuthService authService;
    @Mock
    private CheckEventCommand checkEventCommand;
    @Mock
    private SaveEventCommand saveEventCommand;

    @Test
    @DisplayName("execute - when called save event")
    void executeWhenCalledSaveEvent() {
        doNothing().when(checkEventCommand).execute(any(EventRequest.class));

        var savedEvent = EventMock.build();
        when(saveEventCommand.execute(any(Event.class))).thenReturn(savedEvent);

        var person = AuthenticatedPersonMock.build();
        when(authService.getAuthenticatedPerson()).thenReturn(person);

        var returned = useCase.execute(EventRequestMock.build());

        Assertions.assertNotNull(returned);
        Assertions.assertEquals(savedEvent.getUuid(), returned.getUuid());

        verify(checkEventCommand, times(1)).execute(any(EventRequest.class));
        verify(saveEventCommand, times(1)).execute(any(Event.class));
    }
}