package br.com.events.event.event.business.command.quote_request.message.impl.business_types;

import br.com.events.event.event.adapter.queue.sqs.sender.SqsMessageSender;
import br.com.events.event.event.business.command.event.FindEventCommand;
import br.com.events.event.event.business.command.quote_request.message.SendQuoteRequestMessageCommand;
import br.com.events.event.event.core.exception.event.EventDoesNotExistsException;
import br.com.events.event.event.data.io.inbound.quote.request.QuoteRequestRequest;
import br.com.events.event.event.data.io.outbound.ms_band.message.quote_request.QuoteRequestMsBandMessage;
import br.com.events.event.event.data.io.outbound.ms_mailer.EmailRequestType;
import br.com.events.event.event.data.io.outbound.ms_mailer.RawEmailRequest;
import br.com.events.event.event.data.model.type.BusinessType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class SendBandQuoteRequestMessageCommand implements SendQuoteRequestMessageCommand {

    private final SqsMessageSender sqsMessageSender;
    private final FindEventCommand findEventCommand;

    @Value("${cloud.aws.endpoint.uri.quote.band}")
    private String msBandQuoteRequestQueue;

    @Value("${cloud.aws.endpoint.uri.email-request}")
    private String msMailerEmailRequest;

    @Override
    public BusinessType handledBusinessType() {
        return BusinessType.BAND;
    }

    @Override
    public void sendMessage(
            String eventUuid, String quoteRequestUuid, String bandUuid, Object quoteRequest, boolean isSuccess
    ) {
        if (isSuccess) {
            this.sendCreationMessage(eventUuid, quoteRequestUuid, bandUuid, quoteRequest);
        } else {
            this.sendCreationErrorMessage(eventUuid, bandUuid);
        }
    }

    private void sendCreationErrorMessage(String eventUuid, String bandUuid) {
        var event = findEventCommand.byUuid(eventUuid).orElseThrow(EventDoesNotExistsException::new);

        var keyAndValues = Map.of(
                "bandUuid", bandUuid,
                "eventName", event.getName()
        );
        var message = new RawEmailRequest(EmailRequestType.BAND_QUOTE_REQUEST_ERROR, keyAndValues);
        sqsMessageSender.send(msMailerEmailRequest, message);
    }

    private void sendCreationMessage(String eventUuid, String quoteRequestUuid, String bandUuid, Object quoteRequest) {
        var message = new QuoteRequestMsBandMessage(
                eventUuid,
                quoteRequestUuid,
                bandUuid,
                (QuoteRequestRequest) quoteRequest
        );
        sqsMessageSender.send(msBandQuoteRequestQueue, message);
    }
}
