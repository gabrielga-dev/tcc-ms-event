package br.com.events.event.event.business.command.quote_request.message.answered.impl;

import br.com.events.event.event.adapter.feing.PersonMsAuthFeign;
import br.com.events.event.event.adapter.queue.sqs.sender.SqsMessageSender;
import br.com.events.event.event.business.command.quote_request.message.answered.SendQuoteRequestAnsweredMessageCommand;
import br.com.events.event.event.data.io.outbound.ms_band.message.quote.QuoteAnsweredMessage;
import br.com.events.event.event.data.io.outbound.ms_mailer.RawEmailRequest;
import br.com.events.event.event.data.model.QuoteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SendQuoteRequestAnsweredMessageCommandImpl implements SendQuoteRequestAnsweredMessageCommand {

    private final PersonMsAuthFeign msAuthFeign;
    private final SqsMessageSender sqsMessageSender;

    @Value("${cloud.aws.endpoint.uri.email-request}")
    private String emailEndpoint;

    @Override
    public void sendMessage(QuoteRequest quoteRequest, QuoteAnsweredMessage quoteAnsweredMessage) {
        var person = msAuthFeign.findPersonByUuid(quoteRequest.getEvent().getOwnerUuid());
        var message = new RawEmailRequest(quoteRequest, quoteAnsweredMessage, person);
        sqsMessageSender.send(emailEndpoint, message);
    }
}
