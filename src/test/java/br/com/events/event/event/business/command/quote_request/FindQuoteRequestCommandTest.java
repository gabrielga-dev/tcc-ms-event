package br.com.events.event.event.business.command.quote_request;

import br.com.events.event.event.MockConstants;
import br.com.events.event.event.adapter.repository.QuoteRequestRepository;
import br.com.events.event.event.core.exception.quote_request.QuoteRequestDoesNotExistsException;
import br.com.events.event.event.data.model.QuoteRequestMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link FindQuoteRequestCommand}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class FindQuoteRequestCommandTest {

    @InjectMocks
    private FindQuoteRequestCommand command;

    @Mock
    private QuoteRequestRepository quoteRequestRepository;

    @Test
    @DisplayName("findAll - when called return all records")
    void findAllWhenCalledReturnAllRecords() {
        var quoteRequest = QuoteRequestMock.build();
        when(quoteRequestRepository.findByEventUuid(anyString())).thenReturn(List.of(quoteRequest));

        var returned = command.findAll(MockConstants.STRING);

        Assertions.assertNotNull(returned);
        Assertions.assertFalse(returned.isEmpty());
        Assertions.assertEquals(1, returned.size());
        Assertions.assertEquals(quoteRequest, returned.get(0));

        verify(quoteRequestRepository, times(1)).findByEventUuid(anyString());
    }

    @Test
    @DisplayName("findByUuid - when quote request is found then return not empty optional")
    void findByUuidWhenQuoteRequestIsFoundThenReturnNotEmptyOptional() {
        var quoteRequest = QuoteRequestMock.build();
        when(quoteRequestRepository.findById(anyString())).thenReturn(Optional.of(quoteRequest));

        var returned = command.findByUuid(MockConstants.STRING);

        Assertions.assertNotNull(returned);
        Assertions.assertFalse(returned.isEmpty());
        Assertions.assertEquals(quoteRequest, returned.get());

        verify(quoteRequestRepository, times(1)).findById(anyString());
    }

    @Test
    @DisplayName("findByUuid - when quote request is not found then return empty optional")
    void findByUuidWhenQuoteRequestIsNotFoundThenReturnEmptyOptional() {
        when(quoteRequestRepository.findById(anyString())).thenReturn(Optional.empty());

        var returned = command.findByUuid(MockConstants.STRING);

        Assertions.assertNotNull(returned);
        Assertions.assertTrue(returned.isEmpty());

        verify(quoteRequestRepository, times(1)).findById(anyString());
    }

    @Test
    @DisplayName("findByUuidOrThrow - when quote request is found then return it")
    void findByUuidOrThrowWhenQuoteRequestIsFoundThenReturnNotEmptyOptional() {
        var quoteRequest = QuoteRequestMock.build();
        when(quoteRequestRepository.findById(anyString())).thenReturn(Optional.of(quoteRequest));

        var returned = command.findByUuidOrThrow(MockConstants.STRING);

        Assertions.assertNotNull(returned);
        Assertions.assertEquals(quoteRequest, returned);

        verify(quoteRequestRepository, times(1)).findById(anyString());
    }

    @Test
    @DisplayName("findByUuidOrThrow - when quote request is not found then throw QuoteRequestDoesNotExistsException")
    void findByUuidOrThrowWhenQuoteRequestIsNotFoundThenThrowQuoteRequestDoesNotExistsException() {
        when(quoteRequestRepository.findById(anyString())).thenReturn(Optional.empty());

        Assertions.assertThrows(
                QuoteRequestDoesNotExistsException.class,
                () -> command.findByUuidOrThrow(MockConstants.STRING)
        );

        verify(quoteRequestRepository, times(1)).findById(anyString());
    }
}
