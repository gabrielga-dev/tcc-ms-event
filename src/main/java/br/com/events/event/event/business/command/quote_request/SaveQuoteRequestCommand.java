package br.com.events.event.event.business.command.quote_request;

import br.com.events.event.event.adapter.repository.QuoteRequestRepository;
import br.com.events.event.event.data.model.QuoteRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SaveQuoteRequestCommand {

    private final QuoteRequestRepository quoteRequestRepository;

    public QuoteRequest execute(QuoteRequest toSave){
        return quoteRequestRepository.save(toSave);
    }
}
