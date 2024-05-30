package br.com.events.event.event.adapter.queue.sqs.listener;


import br.com.events.event.event.business.use_case.quote_request.answered.QuoteRequestAnsweredUseCase;
import br.com.events.event.event.data.io.outbound.ms_band.message.quote.QuoteAnsweredMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class QuoterequestAnsweredSqsListener {

    private final ObjectMapper objectMapper;
    private final QuoteRequestAnsweredUseCase quoteRequestAnsweredUseCase;

    @SqsListener("${cloud.aws.endpoint.uri.quote.request.accepted}")
    public void execute(String message) {
        try {
            var object = objectMapper.readValue(message, QuoteAnsweredMessage.class);
            quoteRequestAnsweredUseCase.execute(object);
        } catch (Exception ex) {
            log.info("[END] ERROR! Quote created! message: {} error: {}", message, ex.getMessage());
        }
    }
}
