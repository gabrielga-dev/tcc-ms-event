package br.com.events.event.event.business.command.quote_request.message.create.impl.business_types;

import br.com.events.event.event.MockConstants;
import br.com.events.event.event.adapter.queue.sqs.sender.SqsMessageSender;
import br.com.events.event.event.data.io.inbound.quote.request.create.QuoteRequestRequestMock;
import br.com.events.event.event.data.io.outbound.ms_band.message.quote_request.QuoteRequestMsBandMessage;
import br.com.events.event.event.data.model.type.BusinessType;
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
 * Tests for {@link SendBandQuoteRequestMessageCommand}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class SendBandQuoteRequestMessageCommandTest {

    @InjectMocks
    private SendBandQuoteRequestMessageCommand command;

    @Mock
    private SqsMessageSender sqsMessageSender;

    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(command, "msBandQuoteRequestQueue", MockConstants.STRING);
    }

    @Test
    @DisplayName("handledBusinessType - when called return band value")
    void handledBusinessTypeWhenCalledReturnBandValue() {
        var returned = command.handledBusinessType();
        Assertions.assertNotNull(returned);
        Assertions.assertEquals(BusinessType.BAND, returned);
    }

    @Test
    @DisplayName("sendMessage - when called send message")
    void sendMessageWhenCalledSendMessage() {
        doNothing().when(sqsMessageSender).send(anyString(), any(QuoteRequestMsBandMessage.class));

        Assertions.assertDoesNotThrow(
                () -> command.sendMessage(
                        MockConstants.STRING,
                        MockConstants.STRING,
                        MockConstants.STRING,
                        QuoteRequestRequestMock.build()
                )
        );

        verify(sqsMessageSender, times(1)).send(anyString(), any(QuoteRequestMsBandMessage.class));
    }
}
