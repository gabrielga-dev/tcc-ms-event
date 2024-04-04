package br.com.events.event.event.business.use_case.event.impl;

import br.com.events.event.event.business.command.event.FindEventCommand;
import br.com.events.event.event.business.use_case.event.FindEventByUuidUseCase;
import br.com.events.event.event.core.exception.event.EventDoesNotExistsException;
import br.com.events.event.event.data.io.inbound.event.out.EventResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindEventByUuidUseCaseImpl implements FindEventByUuidUseCase {

    private final FindEventCommand findEventCommand;

    @Override
    public EventResponse execute(String eventUuid) {
        return findEventCommand.byUuid(eventUuid)
                .map(EventResponse::new)
                .orElseThrow(EventDoesNotExistsException::new);
    }
}
