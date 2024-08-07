package br.com.events.event.event.business.use_case.event.impl;

import br.com.events.event.event.MockConstants;
import br.com.events.event.event.business.command.event.FindEventCommand;
import br.com.events.event.event.core.exception.event.EventDoesNotExistsException;
import br.com.events.event.event.data.model.EventMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link FindEventByUuidUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class FindEventByUuidUseCaseImplTest {

    @InjectMocks
    private FindEventByUuidUseCaseImpl useCase;

    @Mock
    private FindEventCommand findEventCommand;

    @Test
    @DisplayName("execute - when event is not found then throws EventDoesNotExistsException")
    void executeWhenEventIsNotFoundThenThrowsEventDoesNotExistsException() {
        when(findEventCommand.byUuid(anyString())).thenReturn(Optional.empty());

        Assertions.assertThrows(
                EventDoesNotExistsException.class,
                () -> useCase.execute(MockConstants.STRING)
        );

        verify(findEventCommand, times(1)).byUuid(anyString());
    }

    @Test
    @DisplayName("execute - when event is found then return event")
    void executeWhenEventIsFoundThenReturnEvent() {
        var event = EventMock.build();
        when(findEventCommand.byUuid(anyString())).thenReturn(Optional.of(event));

        var returned = useCase.execute(MockConstants.STRING);

        Assertions.assertNotNull(returned);
        Assertions.assertEquals(event.getUuid(), returned.getUuid());

        verify(findEventCommand, times(1)).byUuid(anyString());
    }
}