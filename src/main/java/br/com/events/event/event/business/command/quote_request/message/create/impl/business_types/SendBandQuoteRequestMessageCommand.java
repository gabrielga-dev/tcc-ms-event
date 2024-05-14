package br.com.events.event.event.business.command.quote_request.message.create.impl.business_types;

import br.com.events.event.event.adapter.queue.sqs.sender.SqsMessageSender;
import br.com.events.event.event.business.command.quote_request.message.create.SendQuoteRequestMessageCommand;
import br.com.events.event.event.data.io.inbound.quote.request.create.QuoteRequestRequest;
import br.com.events.event.event.data.io.outbound.ms_band.message.quote_request.QuoteRequestMsBandMessage;
import br.com.events.event.event.data.model.type.BusinessType;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SendBandQuoteRequestMessageCommand implements SendQuoteRequestMessageCommand {

    private final SqsMessageSender sqsMessageSender;

    @Value("${cloud.aws.endpoint.uri.quote.band}")
    private String msBandQuoteRequestQueue;

    @Override
    public BusinessType handledBusinessType() {
        return BusinessType.BAND;
    }

    @Override
    public void sendMessage(String eventUuid, String quoteRequestUuid, String bandUuid, Object quoteRequest) {
        var message = new QuoteRequestMsBandMessage(
                eventUuid,
                quoteRequestUuid,
                bandUuid,
                (QuoteRequestRequest) quoteRequest
        );
        sqsMessageSender.send(msBandQuoteRequestQueue, message);
    }
}
