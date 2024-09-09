package br.com.events.event.event.business.command.quote_request;

import br.com.events.event.event.MockConstants;
import br.com.events.event.event.adapter.feing.PersonMsAuthFeign;
import br.com.events.event.event.adapter.queue.sqs.sender.SqsMessageSender;
import br.com.events.event.event.data.io.outbound.msAuth.person.findByUuid.out.PersonResponseMock;
import br.com.events.event.event.data.io.outbound.ms_mailer.RawEmailRequest;
import br.com.events.event.event.data.model.EventMock;
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
 * Tests for {@link SendQuoteRequestEmailCommand}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class SendQuoteRequestEmailCommandTest {

    @InjectMocks
    private SendQuoteRequestEmailCommand command;

    @Mock
    private PersonMsAuthFeign msAuthFeign;
    @Mock
    private SqsMessageSender sqsMessageSender;

    @BeforeEach
    void setup() {
        ReflectionTestUtils.setField(command, "emailEndpoint", MockConstants.STRING);
    }

    @Test
    @DisplayName("execute - when called send message")
    void executeWhenCalledSendMessage() {
        when(msAuthFeign.findPersonByUuid(anyString())).thenReturn(PersonResponseMock.build());
        doNothing().when(sqsMessageSender).send(anyString(), any(RawEmailRequest.class));

        var event = EventMock.build();
        Assertions.assertDoesNotThrow(() -> command.execute(event));

        verify(msAuthFeign, times(1)).findPersonByUuid(anyString());
        verify(sqsMessageSender, times(1)).send(anyString(), any(RawEmailRequest.class));
    }
}
