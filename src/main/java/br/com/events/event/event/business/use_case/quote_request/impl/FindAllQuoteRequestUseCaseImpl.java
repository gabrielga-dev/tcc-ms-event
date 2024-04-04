package br.com.events.event.event.business.use_case.quote_request.impl;

import br.com.events.event.event.business.command.event.FindEventCommand;
import br.com.events.event.event.business.command.quote_request.FindAllQuoteRequestCommand;
import br.com.events.event.event.business.use_case.quote_request.FindAllQuoteRequestUseCase;
import br.com.events.event.event.core.exception.event.EventDoesNotExistsException;
import br.com.events.event.event.core.exception.event.NotEventOwnerException;
import br.com.events.event.event.core.util.AuthUtil;
import br.com.events.event.event.data.io.inbound.quote.response.QuoteRequestTypeResponse;
import br.com.events.event.event.data.model.QuoteRequest;
import br.com.events.event.event.data.model.type.ServiceType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FindAllQuoteRequestUseCaseImpl implements FindAllQuoteRequestUseCase {

    private final FindEventCommand findEventCommand;
    private final FindAllQuoteRequestCommand findAllQuoteRequestCommand;

    @Override
    public List<QuoteRequestTypeResponse> execute(String eventUuid) {
        var event = findEventCommand.byUuid(eventUuid).orElseThrow(EventDoesNotExistsException::new);
        if (!AuthUtil.getAuthenticatedPerson().getUuid().equals(event.getOwnerUuid())) {
            throw new NotEventOwnerException();
        }
        var unsortedRequestResponse = findAllQuoteRequestCommand.findAll(eventUuid);

        Map<ServiceType, List<QuoteRequest>> quoteRequestMap = Arrays.stream(ServiceType.values())
                .collect(
                        Collectors.toMap(
                                serviceType -> serviceType,
                                serviceType -> new ArrayList<>()
                        )
                );

        unsortedRequestResponse.forEach(
                eventQuote -> quoteRequestMap.get(eventQuote.getPk().getServiceType()).add(eventQuote)
        );

        return quoteRequestMap.entrySet()
                .stream()
                .map(QuoteRequestTypeResponse::new)
                .collect(Collectors.toList());
    }
}
