package br.com.events.event.event.business.command.quote_request;

import br.com.events.event.event.adapter.feing.PersonMsAuthFeign;
import br.com.events.event.event.adapter.queue.sqs.sender.SqsMessageSender;
import br.com.events.event.event.data.io.outbound.ms_mailer.RawEmailRequest;
import br.com.events.event.event.data.model.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SendQuoteRequestEmailCommand {


    private final PersonMsAuthFeign msAuthFeign;
    private final SqsMessageSender sqsMessageSender;


    @Value("${cloud.aws.endpoint.uri.email-request}")
    private String emailEndpoint;

    public void execute(Event event){
        var person = msAuthFeign.findPersonByUuid(event.getOwnerUuid());

        var message = new RawEmailRequest(event, person);
        sqsMessageSender.send(emailEndpoint, message);
    }
}
