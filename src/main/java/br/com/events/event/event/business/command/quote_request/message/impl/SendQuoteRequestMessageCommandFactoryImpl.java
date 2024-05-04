package br.com.events.event.event.business.command.quote_request.message.impl;

import br.com.events.event.event.business.command.quote_request.message.SendQuoteRequestMessageCommand;
import br.com.events.event.event.business.command.quote_request.message.SendQuoteRequestMessageCommandFactory;
import br.com.events.event.event.data.model.type.BusinessType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class SendQuoteRequestMessageCommandFactoryImpl implements SendQuoteRequestMessageCommandFactory {

    private final List<SendQuoteRequestMessageCommand> commands;

    @Override
    public Optional<SendQuoteRequestMessageCommand> findMessageSender(BusinessType businessType) {
        return commands.stream()
                .filter(command -> Objects.equals(command.handledBusinessType(), businessType))
                .findFirst();
    }
}
