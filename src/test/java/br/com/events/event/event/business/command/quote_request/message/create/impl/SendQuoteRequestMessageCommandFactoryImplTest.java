package br.com.events.event.event.business.command.quote_request.message.create.impl;

import br.com.events.event.event.business.command.quote_request.message.create.SendQuoteRequestMessageCommand;
import br.com.events.event.event.data.model.type.BusinessType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link SendQuoteRequestMessageCommandFactoryImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class SendQuoteRequestMessageCommandFactoryImplTest {

    @Test
    @DisplayName("findMessageSender - when command is found, then return it")
    void findMessageSenderWhenCommandIsFoundThenReturnIt() {
        var mockedCommand = mock(SendQuoteRequestMessageCommand.class);
        when(mockedCommand.handledBusinessType()).thenReturn(BusinessType.BAND);

        var factory = new SendQuoteRequestMessageCommandFactoryImpl(List.of(mockedCommand));

        var returned = factory.findMessageSender(BusinessType.BAND);

        Assertions.assertNotNull(returned);
        Assertions.assertFalse(returned.isEmpty());
        Assertions.assertEquals(mockedCommand, returned.get());

        verify(mockedCommand, times(1)).handledBusinessType();
    }

    @Test
    @DisplayName("findMessageSender - when command is not found, then return empty optional")
    void findMessageSenderWhenCommandIsNotFoundThenReturnEmptyOptional() {
        var mockedCommand = mock(SendQuoteRequestMessageCommand.class);
        when(mockedCommand.handledBusinessType()).thenReturn(BusinessType.BUFFET);

        var factory = new SendQuoteRequestMessageCommandFactoryImpl(List.of(mockedCommand));

        var returned = factory.findMessageSender(BusinessType.BAND);

        Assertions.assertNotNull(returned);
        Assertions.assertTrue(returned.isEmpty());

        verify(mockedCommand, times(1)).handledBusinessType();
    }
}
