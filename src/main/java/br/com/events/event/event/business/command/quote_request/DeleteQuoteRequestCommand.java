package br.com.events.event.event.business.command.quote_request;

import br.com.events.event.event.adapter.repository.QuoteRequestRepository;
import br.com.events.event.event.business.command.event.FindEventCommand;
import br.com.events.event.event.core.exception.event.EventDoesNotExistsException;
import br.com.events.event.event.core.exception.quote_request.QuoteRequestDoesNotExistsException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DeleteQuoteRequestCommand {

    private final QuoteRequestRepository quoteRequestRepository;
    private final FindEventCommand findEventCommand;
    private final SendQuoteRequestEmailCommand quoteRequestEmailCommand;

    public void execute(String quoteRequestUuid) {
        quoteRequestRepository.findById(quoteRequestUuid)
                .ifPresentOrElse(
                        (quoteRequest) -> {
                            quoteRequestRepository.delete(quoteRequest);
                            var event = findEventCommand.byUuid(quoteRequest.getEvent().getUuid())
                                    .orElseThrow(EventDoesNotExistsException::new);
                            quoteRequestEmailCommand.execute(event);
                        },
                        () -> {
                            throw new QuoteRequestDoesNotExistsException();
                        }
                );

    }
}
