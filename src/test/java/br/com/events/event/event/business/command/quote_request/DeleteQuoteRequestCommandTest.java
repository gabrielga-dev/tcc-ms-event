package br.com.events.event.event.business.command.quote_request;

import br.com.events.event.event.MockConstants;
import br.com.events.event.event.adapter.repository.QuoteRequestRepository;
import br.com.events.event.event.business.command.event.FindEventCommand;
import br.com.events.event.event.core.exception.event.EventDoesNotExistsException;
import br.com.events.event.event.core.exception.quote_request.QuoteRequestDoesNotExistsException;
import br.com.events.event.event.data.model.Event;
import br.com.events.event.event.data.model.EventMock;
import br.com.events.event.event.data.model.QuoteRequest;
import br.com.events.event.event.data.model.QuoteRequestMock;
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
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link DeleteQuoteRequestCommand}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class DeleteQuoteRequestCommandTest {

    @InjectMocks
    private DeleteQuoteRequestCommand command;

    @Mock
    private QuoteRequestRepository quoteRequestRepository;
    @Mock
    private FindEventCommand findEventCommand;
    @Mock
    private SendQuoteRequestEmailCommand quoteRequestEmailCommand;

    @Test
    @DisplayName("execute - when quote request is not found, then throw QuoteRequestDoesNotExistsException")
    void executeWhenQuoteRequestIsNotFoundThenThrowQuoteRequestDoesNotExistsException() {
        when(quoteRequestRepository.findById(anyString())).thenReturn(Optional.empty());

        Assertions.assertThrows(
                QuoteRequestDoesNotExistsException.class,
                () -> command.execute(MockConstants.STRING)
        );

        verify(quoteRequestRepository, times(1)).findById(anyString());
        verify(quoteRequestRepository, times(0)).delete(any(QuoteRequest.class));
        verify(findEventCommand, times(0)).byUuid(anyString());
        verify(quoteRequestEmailCommand, times(0)).execute(any(Event.class));
    }

    @Test
    @DisplayName("execute - when event is not found, then throw EventDoesNotExistsException")
    void executeWhenEventIsNotFoundThenThrowEventDoesNotExistsException() {
        var quoteRequest = QuoteRequestMock.build();
        when(quoteRequestRepository.findById(anyString())).thenReturn(Optional.of(quoteRequest));
        doNothing().when(quoteRequestRepository).delete(quoteRequest);

        when(findEventCommand.byUuid(anyString())).thenReturn(Optional.empty());

        Assertions.assertThrows(
                EventDoesNotExistsException.class,
                () -> command.execute(MockConstants.STRING)
        );

        verify(quoteRequestRepository, times(1)).findById(anyString());
        verify(quoteRequestRepository, times(1)).delete(any(QuoteRequest.class));
        verify(findEventCommand, times(1)).byUuid(anyString());
        verify(quoteRequestEmailCommand, times(0)).execute(any(Event.class));
    }

    @Test
    @DisplayName("execute - when event is found, then throw no exception")
    void executeWhenEventIsNotFoundThenThrowNoException() {
        var quoteRequest = QuoteRequestMock.build();
        when(quoteRequestRepository.findById(anyString())).thenReturn(Optional.of(quoteRequest));
        doNothing().when(quoteRequestRepository).delete(quoteRequest);

        var event = EventMock.build();
        when(findEventCommand.byUuid(anyString())).thenReturn(Optional.of(event));

        doNothing().when(quoteRequestEmailCommand).execute(any(Event.class));

        Assertions.assertDoesNotThrow(() -> command.execute(MockConstants.STRING));

        verify(quoteRequestRepository, times(1)).findById(anyString());
        verify(quoteRequestRepository, times(1)).delete(any(QuoteRequest.class));
        verify(findEventCommand, times(1)).byUuid(anyString());
        verify(quoteRequestEmailCommand, times(1)).execute(any(Event.class));
    }
}
