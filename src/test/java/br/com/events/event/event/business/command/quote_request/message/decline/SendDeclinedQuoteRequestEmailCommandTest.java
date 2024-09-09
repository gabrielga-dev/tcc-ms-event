package br.com.events.event.event.business.command.quote_request.message.decline;

import br.com.events.event.event.MockConstants;
import br.com.events.event.event.adapter.queue.sqs.sender.SqsMessageSender;
import br.com.events.event.event.business.service.AuthService;
import br.com.events.event.event.data.io.inbound.auth.AuthenticatedPersonMock;
import br.com.events.event.event.data.io.inbound.quote.request.decline.DeclineQuoteRequestRequestMock;
import br.com.events.event.event.data.io.outbound.ms_mailer.RawEmailRequest;
import br.com.events.event.event.data.model.QuoteRequestMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link SendDeclinedQuoteRequestEmailCommand}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class SendDeclinedQuoteRequestEmailCommandTest {

    @InjectMocks
    private SendDeclinedQuoteRequestEmailCommand command;

    @Mock
    private SqsMessageSender sqsMessageSender;

    @Mock
    private AuthService authService;

    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(command, "emailRequestQueue", MockConstants.STRING);
    }

    @Test
    @DisplayName("sendMessage - when called send message")
    void sendMessageWhenCalledSendMessage() {
        doNothing().when(sqsMessageSender).send(anyString(), any(RawEmailRequest.class));

        when(authService.getAuthenticatedPerson()).thenReturn(AuthenticatedPersonMock.build());

        var quoteRequest = QuoteRequestMock.build();
        var declineQuoteRequest = DeclineQuoteRequestRequestMock.build();
        Assertions.assertDoesNotThrow(() -> command.sendMessage(quoteRequest, declineQuoteRequest));

        verify(sqsMessageSender, times(1)).send(anyString(), any(RawEmailRequest.class));
    }
}
