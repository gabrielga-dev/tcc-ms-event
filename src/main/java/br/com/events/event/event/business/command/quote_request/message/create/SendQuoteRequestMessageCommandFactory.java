package br.com.events.event.event.business.command.quote_request.message.create;

import br.com.events.event.event.data.model.type.BusinessType;

import java.util.Optional;

public interface SendQuoteRequestMessageCommandFactory {

    Optional<SendQuoteRequestMessageCommand> findMessageSender(BusinessType businessType);
}
