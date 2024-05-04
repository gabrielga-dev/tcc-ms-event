package br.com.events.event.event.business.command.quote_request;

import br.com.events.event.event.adapter.repository.QuoteRequestRepository;
import br.com.events.event.event.adapter.repository.jpa.QuoteRequestJpaRepository;
import br.com.events.event.event.core.exception.quote_request.QuoteRequestDoesNotExistsException;
import br.com.events.event.event.data.model.QuoteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteQuoteRequestCommand {

    private final QuoteRequestRepository quoteRequestRepository;

    public void execute(String quoteRequestUuid) {
        quoteRequestRepository.findById(quoteRequestUuid)
                .ifPresentOrElse(
                        quoteRequestRepository::delete,
                        () -> {
                            throw new QuoteRequestDoesNotExistsException();
                        }
                );
    }

    public void execute(QuoteRequest quoteRequest) {
        quoteRequestRepository.delete(quoteRequest);
    }
}
