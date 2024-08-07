package br.com.events.event.event.business.use_case.event.impl;

import br.com.events.event.event.MockConstants;
import br.com.events.event.event.business.command.event.FindEventCommand;
import br.com.events.event.event.data.model.EventMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link FindEventNamesUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class FindEventNamesUseCaseImplTest {

    @InjectMocks
    private FindEventNamesUseCaseImpl useCase;

    @Mock
    private FindEventCommand findEventCommand;

    @Test
    @DisplayName("execute - when called return map")
    void executeWhenCalledReturnMap() {
        var event = EventMock.build();
        when(findEventCommand.byUuids(anyList())).thenReturn(List.of(event));

        var returned = useCase.execute(List.of(MockConstants.STRING));

        Assertions.assertNotNull(returned);
        Assertions.assertFalse(returned.isEmpty());
        Assertions.assertEquals(event.getName(), returned.get(event.getUuid()));

        verify(findEventCommand, times(1)).byUuids(anyList());
    }
}