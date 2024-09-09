package br.com.events.event.event.business.use_case.quote_request.decline.impl;

import br.com.events.event.event.MockConstants;
import br.com.events.event.event.business.command.quote_request.FindQuoteRequestCommand;
import br.com.events.event.event.business.command.quote_request.SaveQuoteRequestCommand;
import br.com.events.event.event.business.command.quote_request.message.decline.SendDeclinedQuoteRequestEmailCommand;
import br.com.events.event.event.core.exception.quote_request.QuoteRequestDoesNotExistsException;
import br.com.events.event.event.data.io.inbound.quote.request.decline.DeclineQuoteRequestRequest;
import br.com.events.event.event.data.io.inbound.quote.request.decline.DeclineQuoteRequestRequestMock;
import br.com.events.event.event.data.model.QuoteRequest;
import br.com.events.event.event.data.model.QuoteRequestMock;
import br.com.events.event.event.data.model.type.QuoteRequestStatusType;
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
 * Tests for {@link DeclineQuoteRequestUseCaseImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class DeclineQuoteRequestUseCaseImplTest {

    @InjectMocks
    private DeclineQuoteRequestUseCaseImpl useCase;

    @Mock
    private FindQuoteRequestCommand findQuoteRequestCommand;
    @Mock
    private SaveQuoteRequestCommand saveQuoteRequestCommand;
    @Mock
    private SendDeclinedQuoteRequestEmailCommand quoteRequestEmailCommand;

    @Test
    @DisplayName("execute - when quote request is not found, then throw QuoteRequestDoesNotExistsException")
    void executeWhenQuoteRequestIsNotFoundThenThrowQuoteRequestDoesNotExistsException() {
        when(findQuoteRequestCommand.findByUuid(anyString())).thenReturn(Optional.empty());

        var request = DeclineQuoteRequestRequestMock.build();
        Assertions.assertThrows(
                QuoteRequestDoesNotExistsException.class,
                () -> useCase.execute(MockConstants.STRING, request)
        );

        verify(findQuoteRequestCommand, times(1)).findByUuid(anyString());
        verify(saveQuoteRequestCommand, times(0)).execute(any(QuoteRequest.class));
        verify(quoteRequestEmailCommand, times(0))
                .sendMessage(any(QuoteRequest.class), any(DeclineQuoteRequestRequest.class));
    }

    @Test
    @DisplayName("execute - when quote request is already answered, then throw QuoteRequestDoesNotExistsException")
    void executeWhenQuoteRequestIsAlreadyAnsweredThenThrowQuoteRequestDoesNotExistsException() {
        var quoteRequest = QuoteRequestMock.build();
        quoteRequest.setStatus(QuoteRequestStatusType.ANSWERED);
        when(findQuoteRequestCommand.findByUuid(anyString())).thenReturn(Optional.of(quoteRequest));

        var request = DeclineQuoteRequestRequestMock.build();
        Assertions.assertThrows(
                QuoteRequestDoesNotExistsException.class,
                () -> useCase.execute(MockConstants.STRING, request)
        );

        verify(findQuoteRequestCommand, times(1)).findByUuid(anyString());
        verify(saveQuoteRequestCommand, times(0)).execute(any(QuoteRequest.class));
        verify(quoteRequestEmailCommand, times(0))
                .sendMessage(any(QuoteRequest.class), any(DeclineQuoteRequestRequest.class));
    }

    @Test
    @DisplayName("execute - when everything is ok, then decline quote request")
    void executeWhenEverythingIsOkThenDeclineQuoteRequest() {
        var quoteRequest = QuoteRequestMock.build();
        quoteRequest.setStatus(QuoteRequestStatusType.NON_ANSWERED);
        when(findQuoteRequestCommand.findByUuid(anyString())).thenReturn(Optional.of(quoteRequest));

        when(saveQuoteRequestCommand.execute(any(QuoteRequest.class))).thenReturn(quoteRequest);

        doNothing().when(quoteRequestEmailCommand)
                .sendMessage(any(QuoteRequest.class), any(DeclineQuoteRequestRequest.class));

        var request = DeclineQuoteRequestRequestMock.build();
        Assertions.assertDoesNotThrow(
                () -> useCase.execute(MockConstants.STRING, request)
        );

        verify(findQuoteRequestCommand, times(1)).findByUuid(anyString());
        verify(saveQuoteRequestCommand, times(1)).execute(any(QuoteRequest.class));
        verify(quoteRequestEmailCommand, times(1))
                .sendMessage(any(QuoteRequest.class), any(DeclineQuoteRequestRequest.class));
    }
}