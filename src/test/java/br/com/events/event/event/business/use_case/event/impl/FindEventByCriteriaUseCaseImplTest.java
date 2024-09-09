package br.com.events.event.event.business.use_case.event.impl;

import br.com.events.event.event.business.command.event.FindEventCommand;
import br.com.events.event.event.business.service.AuthService;
import br.com.events.event.event.data.io.inbound.auth.AuthenticatedPersonMock;
import br.com.events.event.event.data.io.inbound.event.in.EventCriteria;
import br.com.events.event.event.data.io.inbound.event.in.EventCriteriaMock;
import br.com.events.event.event.data.model.EventMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link CreateEventUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class FindEventByCriteriaUseCaseImplTest {

    @InjectMocks
    private FindEventByCriteriaUseCaseImpl useCase;

    @Mock
    private AuthService authService;
    @Mock
    private FindEventCommand findEventCommand;

    @Test
    @DisplayName("execute - when user is not logged, then set owner uuid criteria as null")
    void executeWhenUserIsNotLoggedThenSetOwnerUuidCriteriaAsNull() {
        when(authService.getAuthenticatedPerson()).thenReturn(null);

        var event = EventMock.build();
        when(findEventCommand.byCriteria(any(EventCriteria.class), any(Pageable.class))).thenReturn(
                new PageImpl<>(List.of(event))
        );

        var mockedCriteria = mock(EventCriteria.class);
        doNothing().when(mockedCriteria).setOwnerUuid(null);
        var returned = useCase.execute(mockedCriteria, mock(Pageable.class));

        Assertions.assertNotNull(returned);
        Assertions.assertNotNull(returned.getContent());
        Assertions.assertFalse(returned.getContent().isEmpty());
        Assertions.assertEquals(1, returned.getContent().size());
        Assertions.assertEquals(event.getUuid(), returned.getContent().get(0).getUuid());

        verify(mockedCriteria, times(1)).setOwnerUuid(null);
        verify(authService, times(1)).getAuthenticatedPerson();
        verify(findEventCommand, times(1)).byCriteria(any(EventCriteria.class), any(Pageable.class));
    }

    @Test
    @DisplayName("execute - when user is logged, then do not set owner uuid criteria as null")
    void executeWhenUserIsLoggedThenDoNotSetOwnerUuidCriteriaAsNull() {
        when(authService.getAuthenticatedPerson()).thenReturn(AuthenticatedPersonMock.build());

        var event = EventMock.build();
        when(findEventCommand.byCriteria(any(EventCriteria.class), any(Pageable.class))).thenReturn(
                new PageImpl<>(List.of(event))
        );

        var criteria = EventCriteriaMock.build();
        var returned = useCase.execute(criteria, mock(Pageable.class));

        Assertions.assertNotNull(returned);
        Assertions.assertNotNull(returned.getContent());
        Assertions.assertFalse(returned.getContent().isEmpty());
        Assertions.assertEquals(1, returned.getContent().size());
        Assertions.assertEquals(event.getUuid(), returned.getContent().get(0).getUuid());

        verify(authService, times(1)).getAuthenticatedPerson();
        verify(findEventCommand, times(1)).byCriteria(any(EventCriteria.class), any(Pageable.class));
    }
}