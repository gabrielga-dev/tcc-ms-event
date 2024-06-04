package br.com.events.event.event.business.command.quote.impl;

import br.com.events.event.event.adapter.queue.sqs.sender.SqsMessageSender;
import br.com.events.event.event.business.command.quote.SendQuoteEventAnswerMessageCommand;
import br.com.events.event.event.data.io.outbound.ms_band.message.quote.QuoteEventAnswerMessage;
import br.com.events.event.event.data.model.QuoteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SendQuoteEventAnswerMessageCommandImpl implements SendQuoteEventAnswerMessageCommand {

    private final SqsMessageSender sqsMessageSender;

    @Value("${cloud.aws.endpoint.uri.quote.request.event.answered}")
    private String quoteEventAnswer;

    @Override
    public void execute(QuoteRequest quoteRequest, boolean hired) {
        sqsMessageSender.send(quoteEventAnswer, new QuoteEventAnswerMessage(quoteRequest.getQuoteUuid(), hired));
    }
}
