package br.com.events.event.event.business.use_case.quote_request.delete.impl;

import br.com.events.event.event.business.command.quote_request.DeleteQuoteRequestCommand;
import br.com.events.event.event.business.command.quote_request.FindQuoteRequestCommand;
import br.com.events.event.event.core.exception.quote_request.QuoteRequestDoesNotExistsException;
import br.com.events.event.event.data.io.outbound.ms_band.message.quote_request.QuoteRequestCreationErrorMessageMock;
import br.com.events.event.event.data.model.QuoteRequestMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link DeleteQuoteRequestUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class DeleteQuoteRequestUseCaseImplTest {

    @InjectMocks
    private DeleteQuoteRequestUseCaseImpl useCase;

    @Mock
    private FindQuoteRequestCommand findQuoteRequestCommand;
    @Mock
    private DeleteQuoteRequestCommand deleteQuoteRequestCommand;

    @Test
    @DisplayName("execute - when quote request is not found, then throw QuoteRequestDoesNotExistsException")
    void executeWhenQuoteRequestIsNotFoundThenThrowQuoteRequestDoesNotExistsException() {
        when(findQuoteRequestCommand.findByUuid(anyString())).thenReturn(Optional.empty());

        var request = QuoteRequestCreationErrorMessageMock.build();
        Assertions.assertThrows(
                QuoteRequestDoesNotExistsException.class,
                () -> useCase.execute(request)
        );

        verify(findQuoteRequestCommand, times(1)).findByUuid(anyString());
        verify(deleteQuoteRequestCommand, times(0)).execute(anyString());
    }

    @Test
    @DisplayName("execute - when quote request is found, then delete quote request")
    void executeWhenQuoteRequestIsFoundThenDeleteQuoteRequest() {
        var quoteRequest = QuoteRequestMock.build();
        when(findQuoteRequestCommand.findByUuid(anyString())).thenReturn(Optional.of(quoteRequest));

        doNothing().when(deleteQuoteRequestCommand).execute(anyString());

        var request = QuoteRequestCreationErrorMessageMock.build();
        Assertions.assertDoesNotThrow(
                () -> useCase.execute(request)
        );

        verify(findQuoteRequestCommand, times(1)).findByUuid(anyString());
        verify(deleteQuoteRequestCommand, times(1)).execute(anyString());
    }
}