package br.com.events.event.event.business.use_case.quote_request.answered.impl;

import br.com.events.event.event.business.command.quote_request.FindQuoteRequestCommand;
import br.com.events.event.event.business.command.quote_request.SaveQuoteRequestCommand;
import br.com.events.event.event.business.command.quote_request.message.answered.SendQuoteRequestAnsweredMessageCommand;
import br.com.events.event.event.core.exception.quote_request.QuoteRequestDoesNotExistsException;
import br.com.events.event.event.data.io.outbound.ms_band.message.quote.QuoteAnsweredMessage;
import br.com.events.event.event.data.io.outbound.ms_band.message.quote.QuoteAnsweredMessageMock;
import br.com.events.event.event.data.model.QuoteRequest;
import br.com.events.event.event.data.model.QuoteRequestMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link QuoteRequestAnsweredUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class QuoteRequestAnsweredUseCaseImplTest {

    @InjectMocks
    private QuoteRequestAnsweredUseCaseImpl useCase;

    @Mock
    private FindQuoteRequestCommand findQuoteRequestCommand;
    @Mock
    private SaveQuoteRequestCommand saveQuoteRequestCommand;
    @Mock
    private SendQuoteRequestAnsweredMessageCommand sendQuoteRequestAnsweredMessageCommand;

    @Test
    @DisplayName("execute - when quote request is not found, then throw QuoteRequestDoesNotExistsException")
    void executeWhenQuoteRequestIsNotFoundThenThrowQuoteRequestDoesNotExistsException() {
        when(findQuoteRequestCommand.findByUuid(anyString())).thenReturn(Optional.empty());

        var message = QuoteAnsweredMessageMock.build();
        Assertions.assertThrows(
                QuoteRequestDoesNotExistsException.class,
                () -> useCase.execute(message)
        );

        verify(findQuoteRequestCommand, times(1)).findByUuid(anyString());
        verify(saveQuoteRequestCommand, times(0)).execute(any(QuoteRequest.class));
        verify(sendQuoteRequestAnsweredMessageCommand, times(0))
                .sendMessage(any(QuoteRequest.class), any(QuoteAnsweredMessage.class));
    }

    @Test
    @DisplayName("execute - when event is not active, then do not update quote request")
    void executeWhenEventIsNotActiveThenDoNotUpdateQuoteRequest() {
        var quoteRequest = QuoteRequestMock.build();
        quoteRequest.getEvent().setActive(Boolean.FALSE);
        when(findQuoteRequestCommand.findByUuid(anyString())).thenReturn(Optional.of(quoteRequest));

        var message = QuoteAnsweredMessageMock.build();
        Assertions.assertDoesNotThrow(
                () -> useCase.execute(message)
        );

        verify(findQuoteRequestCommand, times(1)).findByUuid(anyString());
        verify(saveQuoteRequestCommand, times(0)).execute(any(QuoteRequest.class));
        verify(sendQuoteRequestAnsweredMessageCommand, times(0))
                .sendMessage(any(QuoteRequest.class), any(QuoteAnsweredMessage.class));
    }

    @Test
    @DisplayName("execute - when event already happened, then do not update quote request")
    void executeWhenEventAlreadyHappenedThenDoNotUpdateQuoteRequest() {
        var quoteRequest = QuoteRequestMock.build();
        quoteRequest.getEvent().setActive(Boolean.TRUE);
        quoteRequest.getEvent().setDate(LocalDateTime.now().minusYears(1L));
        when(findQuoteRequestCommand.findByUuid(anyString())).thenReturn(Optional.of(quoteRequest));

        var message = QuoteAnsweredMessageMock.build();
        Assertions.assertDoesNotThrow(
                () -> useCase.execute(message)
        );

        verify(findQuoteRequestCommand, times(1)).findByUuid(anyString());
        verify(saveQuoteRequestCommand, times(0)).execute(any(QuoteRequest.class));
        verify(sendQuoteRequestAnsweredMessageCommand, times(0))
                .sendMessage(any(QuoteRequest.class), any(QuoteAnsweredMessage.class));
    }

    @Test
    @DisplayName("execute - when everything is ok, then update quote request")
    void executeWhenEverythingIsOkThenUpdateQuoteRequest() {
        var quoteRequest = QuoteRequestMock.build();
        quoteRequest.getEvent().setActive(Boolean.TRUE);
        quoteRequest.getEvent().setDate(LocalDateTime.now().plusYears(1L));
        when(findQuoteRequestCommand.findByUuid(anyString())).thenReturn(Optional.of(quoteRequest));

        when(saveQuoteRequestCommand.execute(any(QuoteRequest.class))).thenReturn(quoteRequest);

        doNothing().when(sendQuoteRequestAnsweredMessageCommand)
                .sendMessage(any(QuoteRequest.class), any(QuoteAnsweredMessage.class));

        var message = QuoteAnsweredMessageMock.build();
        Assertions.assertDoesNotThrow(
                () -> useCase.execute(message)
        );

        verify(findQuoteRequestCommand, times(1)).findByUuid(anyString());
        verify(saveQuoteRequestCommand, times(1)).execute(any(QuoteRequest.class));
        verify(sendQuoteRequestAnsweredMessageCommand, times(1))
                .sendMessage(any(QuoteRequest.class), any(QuoteAnsweredMessage.class));
    }
}