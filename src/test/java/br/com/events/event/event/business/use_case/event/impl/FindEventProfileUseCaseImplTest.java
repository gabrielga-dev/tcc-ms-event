package br.com.events.event.event.business.use_case.event.impl;

import br.com.events.event.event.MockConstants;
import br.com.events.event.event.business.command.address.BuildAddressResponseCommand;
import br.com.events.event.event.business.command.event.FindEventCommand;
import br.com.events.event.event.core.exception.event.EventDoesNotExistsException;
import br.com.events.event.event.data.io.inbound.address.out.AddressResponseMock;
import br.com.events.event.event.data.model.Address;
import br.com.events.event.event.data.model.EventMock;
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
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link FindEventProfileUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class FindEventProfileUseCaseImplTest {

    @InjectMocks
    private FindEventProfileUseCaseImpl useCase;

    @Mock
    private FindEventCommand findEventCommand;
    @Mock
    private BuildAddressResponseCommand buildAddressResponseCommand;

    @Test
    @DisplayName("execute - when event is not found then throws EventDoesNotExistsException")
    void executeWhenEventIsNotFoundThenThrowsEventDoesNotExistsException() {
        when(findEventCommand.byUuid(anyString())).thenReturn(Optional.empty());

        Assertions.assertThrows(
                EventDoesNotExistsException.class,
                () -> useCase.execute(MockConstants.STRING)
        );

        verify(findEventCommand, times(1)).byUuid(anyString());
        verify(buildAddressResponseCommand, times(0)).execute(any(Address.class));
    }

    @Test
    @DisplayName("execute - when event not found then return event profile")
    void executeWhenEventIsFoundThenReturnEventProfile() {
        var event = EventMock.build();
        when(findEventCommand.byUuid(anyString())).thenReturn(Optional.of(event));

        var addressResponse = AddressResponseMock.build();
        when(buildAddressResponseCommand.execute(any(Address.class))).thenReturn(addressResponse);

        var returned = useCase.execute(MockConstants.STRING);

        Assertions.assertNotNull(returned);
        Assertions.assertEquals(event.getUuid(), returned.getUuid());
        Assertions.assertEquals(addressResponse, returned.getAddress());

        verify(findEventCommand, times(1)).byUuid(anyString());
        verify(buildAddressResponseCommand, times(1)).execute(any(Address.class));
    }
}