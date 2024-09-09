package br.com.events.event.event.business.command.quote_request.message.decline;


import br.com.events.event.event.adapter.queue.sqs.sender.SqsMessageSender;
import br.com.events.event.event.business.service.AuthService;
import br.com.events.event.event.data.io.inbound.quote.request.decline.DeclineQuoteRequestRequest;
import br.com.events.event.event.data.io.outbound.ms_mailer.RawEmailRequest;
import br.com.events.event.event.data.model.QuoteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SendDeclinedQuoteRequestEmailCommand {

    private final SqsMessageSender sqsMessageSender;
    private final AuthService authService;

    @Value("${cloud.aws.endpoint.uri.email-request}")
    private String emailRequestQueue;

    public void sendMessage(QuoteRequest quoteRequest, DeclineQuoteRequestRequest declineQuoteRequest) {
        var message = new RawEmailRequest(quoteRequest, declineQuoteRequest, authService.getAuthenticatedPerson());
        sqsMessageSender.send(emailRequestQueue, message);
    }
}
