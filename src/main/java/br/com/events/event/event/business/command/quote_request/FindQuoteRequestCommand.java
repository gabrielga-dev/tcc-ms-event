package br.com.events.event.event.business.command.quote_request;

import br.com.events.event.event.adapter.repository.QuoteRequestRepository;
import br.com.events.event.event.core.exception.quote_request.QuoteRequestDoesNotExistsException;
import br.com.events.event.event.data.model.QuoteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FindQuoteRequestCommand {

    private final QuoteRequestRepository quoteRequestRepository;

    public List<QuoteRequest> findAll(String eventUuid) {
        return quoteRequestRepository.findByEventUuid(eventUuid);
    }

    public Optional<QuoteRequest> findByUuid(String quoteRequestUuid) {
        return quoteRequestRepository.findById(quoteRequestUuid);
    }

    public QuoteRequest findByUuidOrThrow(String quoteRequestUuid) {
        return this.findByUuid(quoteRequestUuid).orElseThrow(QuoteRequestDoesNotExistsException::new);
    }
}
