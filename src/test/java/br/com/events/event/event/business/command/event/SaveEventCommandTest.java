package br.com.events.event.event.business.command.event;

import br.com.events.event.event.adapter.repository.EventRepository;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link SaveEventCommand}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class SaveEventCommandTest {

    @InjectMocks
    private SaveEventCommand command;

    @Mock
    private EventRepository eventRepository;

    @Test
    @DisplayName("execute - when called saves event")
    void executeWhenCalledSavesEvent(){
        var event = EventMock.build();
        when(eventRepository.save(any(Event.class))).thenReturn(event);

        var returned = command.execute(event);

        Assertions.assertNotNull(returned);
        Assertions.assertEquals(event, returned);

        verify(eventRepository, times(1)).save(any(Event.class));
    }
}
