package br.com.events.event.event.business.command.event;

import br.com.events.event.event.MockConstants;
import br.com.events.event.event.adapter.repository.EventRepository;
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

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link FindEventCommand}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)

public class FindEventCommandTest {
    @InjectMocks
    private FindEventCommand command;

    @Mock
    private EventRepository eventRepository;

    @Test
    @DisplayName("byCriteria - when called return page of events")
    void byCriteriaWhenCalledReturnPageOfEvents() {
        var event = EventMock.build();
        when(
                eventRepository.findByCriteria(
                        anyString(),
                        any(LocalDateTime.class),
                        any(LocalDateTime.class),
                        anyBoolean(),
                        anyString(),
                        any(Pageable.class)
                )
        ).thenReturn(
                new PageImpl<>(List.of(event))
        );

        var criteria = EventCriteriaMock.build();
        var returned = command.byCriteria(criteria, mock(Pageable.class));

        Assertions.assertNotNull(returned);
        Assertions.assertNotNull(returned.getContent());
        Assertions.assertFalse(returned.getContent().isEmpty());
        Assertions.assertEquals(1, returned.getContent().size());
        Assertions.assertEquals(event, returned.getContent().get(0));

        verify(eventRepository, times(1)).findByCriteria(
                anyString(),
                any(LocalDateTime.class),
                any(LocalDateTime.class),
                anyBoolean(),
                anyString(),
                any(Pageable.class)
        );
    }

    @Test
    @DisplayName("byUuid - when called return optional")
    void byUuidWhenCalledReturnOptional() {
        var event = EventMock.build();
        when(eventRepository.findById(anyString())).thenReturn(Optional.of(event));

        var returned = command.byUuid(MockConstants.STRING);

        Assertions.assertNotNull(returned);
        Assertions.assertFalse(returned.isEmpty());
        Assertions.assertNotNull(returned.get());
        Assertions.assertEquals(event, returned.get());

        verify(eventRepository, times(1)).findById(anyString());
    }

    @Test
    @DisplayName("byUuids - when called return events")
    void byUuidsWhenCalledReturnEvents() {
        var event = EventMock.build();
        when(eventRepository.findByUuids(anyList())).thenReturn(List.of(event));

        var returned = command.byUuids(List.of(MockConstants.STRING));

        Assertions.assertNotNull(returned);
        Assertions.assertFalse(returned.isEmpty());
        Assertions.assertEquals(1, returned.size());
        Assertions.assertEquals(event, returned.get(0));

        verify(eventRepository, times(1)).findByUuids(anyList());
    }

    @Test
    @DisplayName("findAll - when called return events")
    void findAllWhenCalledReturnEvents() {
        var event = EventMock.build();
        when(eventRepository.findByOwnerUuid(anyString())).thenReturn(List.of(event));

        var returned = command.findAll(MockConstants.STRING);

        Assertions.assertNotNull(returned);
        Assertions.assertFalse(returned.isEmpty());
        Assertions.assertEquals(1, returned.size());
        Assertions.assertEquals(event, returned.get(0));

        verify(eventRepository, times(1)).findByOwnerUuid(anyString());
    }
}
