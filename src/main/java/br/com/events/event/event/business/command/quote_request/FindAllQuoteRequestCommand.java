package br.com.events.event.event.business.command.quote_request;

import br.com.events.event.event.adapter.repository.QuoteRequestRepository;
import br.com.events.event.event.data.model.QuoteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FindAllQuoteRequestCommand {

    private final QuoteRequestRepository quoteRequestRepository;

    public List<QuoteRequest> findAll(String eventUuid) {
        return quoteRequestRepository.findByEventUuid(eventUuid);
    }
}
