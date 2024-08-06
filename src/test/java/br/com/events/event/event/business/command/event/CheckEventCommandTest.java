package br.com.events.event.event.business.command.event;


import br.com.events.event.event.business.command.address.CheckAddressCommand;
import br.com.events.event.event.core.exception.event.DateOnPastException;
import br.com.events.event.event.core.util.DateUtil;
import br.com.events.event.event.data.io.inbound.address.IAddress;
import br.com.events.event.event.data.io.inbound.event.in.EventRequestMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Tests for {@link CheckEventCommand}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class CheckEventCommandTest {

    @InjectMocks
    private CheckEventCommand command;

    @Mock
    private CheckAddressCommand checkAddressCommand;

    @Test
    @DisplayName("execute - when date is before now, then throw DateOnPastException")
    void executeWhenDateIsBeforeNowThenThrowDateOnPastException() {
        var request = EventRequestMock.build();
        request.setDateTimestamp(DateUtil.localDateTimeToTimestamp(LocalDateTime.now().minusYears(100)));

        Assertions.assertThrows(
                DateOnPastException.class,
                () -> command.execute(request)
        );

        verify(checkAddressCommand, never()).execute(any(IAddress.class));
    }

    @Test
    @DisplayName("execute - when date is on future, then check address")
    void executeWhenDateIsOnFutureThenCheckAddress() {
        var request = EventRequestMock.build();
        request.setDateTimestamp(DateUtil.localDateTimeToTimestamp(LocalDateTime.now().plusYears(100)));

        doNothing().when(checkAddressCommand).execute(any(IAddress.class));

        Assertions.assertDoesNotThrow(
                () -> command.execute(request)
        );

        verify(checkAddressCommand, times(1)).execute(any(IAddress.class));
    }
}
