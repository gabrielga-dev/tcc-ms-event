package br.com.events.event.event.business.command.quote_request.message.answered.impl;


import br.com.events.event.event.MockConstants;
import br.com.events.event.event.adapter.feing.PersonMsAuthFeign;
import br.com.events.event.event.adapter.queue.sqs.sender.SqsMessageSender;
import br.com.events.event.event.data.io.outbound.msAuth.person.findByUuid.out.PersonResponseMock;
import br.com.events.event.event.data.io.outbound.ms_band.message.quote.QuoteAnsweredMessageMock;
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
 * Tests for {@link SendQuoteRequestAnsweredMessageCommandImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class SendQuoteRequestAnsweredMessageCommandImplTest {

    @InjectMocks
    private SendQuoteRequestAnsweredMessageCommandImpl command;

    @Mock
    private PersonMsAuthFeign msAuthFeign;
    @Mock
    private SqsMessageSender sqsMessageSender;

    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(command, "emailEndpoint", MockConstants.STRING);
    }

    @Test
    @DisplayName("execute - when called saves event")
    void executeWhenCalledSavesEvent() {
        when(msAuthFeign.findPersonByUuid(anyString())).thenReturn(PersonResponseMock.build());

        doNothing().when(sqsMessageSender).send(anyString(), any(RawEmailRequest.class));

        var quoteRequest = QuoteRequestMock.build();
        var quoteAnsweredMessage = QuoteAnsweredMessageMock.build();
        Assertions.assertDoesNotThrow(
                () -> command.sendMessage(quoteRequest, quoteAnsweredMessage)
        );

        verify(msAuthFeign, times(1)).findPersonByUuid(anyString());
        verify(sqsMessageSender, times(1)).send(anyString(), any(RawEmailRequest.class));
    }
}
