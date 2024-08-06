package br.com.events.event.event.business.command.quote.impl;


import br.com.events.event.event.MockConstants;
import br.com.events.event.event.adapter.queue.sqs.sender.SqsMessageSender;
import br.com.events.event.event.data.io.outbound.ms_band.message.quote.QuoteEventAnswerMessage;
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

/**
 * Tests for {@link SendQuoteEventAnswerMessageCommandImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class SendQuoteEventAnswerMessageCommandImplTest {

    @InjectMocks
    private SendQuoteEventAnswerMessageCommandImpl command;

    @Mock
    private SqsMessageSender sqsMessageSender;

    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(command, "quoteEventAnswer", MockConstants.STRING);
    }

    @Test
    @DisplayName("execute - when called saves event")
    void executeWhenCalledSavesEvent() {
        doNothing().when(sqsMessageSender).send(anyString(), any(QuoteEventAnswerMessage.class));

        var quoteRequest = QuoteRequestMock.build();
        Assertions.assertDoesNotThrow(
                () -> command.execute(quoteRequest, MockConstants.BOOLEAN)
        );

        verify(sqsMessageSender, times(1)).send(anyString(), any(QuoteEventAnswerMessage.class));
    }
}
