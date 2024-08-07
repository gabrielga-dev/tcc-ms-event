package br.com.events.event.event.business.use_case.quote.impl;

import br.com.events.event.event.business.command.event.FindEventCommand;
import br.com.events.event.event.business.service.AuthService;
import br.com.events.event.event.data.io.inbound.auth.AuthenticatedPersonMock;
import br.com.events.event.event.data.model.EventMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link GenerateDashboardUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class GenerateDashboardUseCaseImplTest {

    @InjectMocks
    private GenerateDashboardUseCaseImpl useCase;

    @Mock
    private AuthService authService;
    @Mock
    private FindEventCommand findEventCommand;

    @Test
    @DisplayName("execute - when called return dashBoard")
    void executeWhenCalledReturnDashBoard() {
        when(authService.getAuthenticatedPerson()).thenReturn(AuthenticatedPersonMock.build());

        var event = EventMock.build();
        when(findEventCommand.findAll(anyString())).thenReturn(List.of(event));

        var returned = useCase.execute();

        Assertions.assertNotNull(returned);

        verify(authService, times(1)).getAuthenticatedPerson();
        verify(findEventCommand, times(1)).findAll(anyString());
    }
}