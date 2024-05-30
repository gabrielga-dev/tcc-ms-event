package br.com.events.event.event.adapter.queue.sqs.listener;


import br.com.events.event.event.business.use_case.quote_request.delete.DeleteQuoteRequestUseCase;
import br.com.events.event.event.data.io.outbound.ms_band.message.quote_request.QuoteRequestCreationErrorMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.aws.messaging.listener.annotation.SqsListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CreateQuoteRequestDlqSqsListener {

    private final ObjectMapper objectMapper;
    private final DeleteQuoteRequestUseCase deleteQuoteRequestUseCase;

    @SqsListener("${cloud.aws.endpoint.uri.quote.band-dlq}")
    public void receiveQuoteRequestCreationDlqMessage(String message) {
        try {
            var object = objectMapper.readValue(message, QuoteRequestCreationErrorMessage.class);
            deleteQuoteRequestUseCase.execute(object);
        } catch (Exception ex) {
            log.info("[END] ERROR! Quote request not deleted! message: {} error: {}", message, ex.getMessage());
        }
    }
}
