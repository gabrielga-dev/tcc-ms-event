package br.com.events.event.event.business.use_case.quote_request.create.impl;

import br.com.events.event.event.business.command.event.FindEventCommand;
import br.com.events.event.event.business.command.quote_request.SaveQuoteRequestCommand;
import br.com.events.event.event.business.command.quote_request.message.create.SendQuoteRequestMessageCommandFactory;
import br.com.events.event.event.business.use_case.quote_request.create.CreateQuoteRequestUseCase;
import br.com.events.event.event.core.exception.event.BusinessTypeNotSupportedYetException;
import br.com.events.event.event.core.exception.event.EventDoesNotExistsException;
import br.com.events.event.event.core.exception.event.NotEventOwnerException;
import br.com.events.event.event.core.util.AuthUtil;
import br.com.events.event.event.data.io.inbound.quote.request.create.QuoteRequestRequest;
import br.com.events.event.event.data.model.QuoteRequest;
import br.com.events.event.event.data.model.type.BusinessType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CreateBandQuoteRequestUseCaseImpl implements CreateQuoteRequestUseCase {

    private final FindEventCommand findEventCommand;
    private final SendQuoteRequestMessageCommandFactory sendQuoteRequestMessageCommandFactory;
    private final SaveQuoteRequestCommand saveQuoteRequestCommand;

    @Override
    public BusinessType getHandledBusinessType() {
        return BusinessType.BAND;
    }

    @Override
    public void execute(String eventUuid, String bandUuid, Object unCastedQuoteRequest) {
        var quoteRequest = (QuoteRequestRequest) unCastedQuoteRequest;
        var event = findEventCommand.byUuid(eventUuid).orElseThrow(EventDoesNotExistsException::new);

        if (!Objects.equals(AuthUtil.getAuthenticatedPerson().getUuid(), event.getOwnerUuid())) {
            throw new NotEventOwnerException();
        }

        var toSave = new QuoteRequest(event, BusinessType.BAND, bandUuid);

        sendQuoteRequestMessageCommandFactory.findMessageSender(BusinessType.BAND)
                .orElseThrow(BusinessTypeNotSupportedYetException::new)
                .sendMessage(eventUuid, toSave.getUuid(), bandUuid, quoteRequest);

        saveQuoteRequestCommand.execute(toSave);
    }
}
