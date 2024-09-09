package br.com.events.event.event.business.use_case.event.impl;

import br.com.events.event.event.business.command.event.FindEventCommand;
import br.com.events.event.event.business.service.AuthService;
import br.com.events.event.event.business.use_case.event.FindEventByCriteriaUseCase;
import br.com.events.event.event.data.io.inbound.event.in.EventCriteria;
import br.com.events.event.event.data.io.inbound.event.out.EventResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@RequiredArgsConstructor
public class FindEventByCriteriaUseCaseImpl implements FindEventByCriteriaUseCase {

    private final AuthService authService;
    private final FindEventCommand findEventCommand;

    @Override
    public Page<EventResponse> execute(EventCriteria criteria, Pageable pageable) {
        if (Objects.isNull(authService.getAuthenticatedPerson())) {
            criteria.setOwnerUuid(null);
        }
        return findEventCommand.byCriteria(criteria, pageable)
                .map(EventResponse::new);
    }
}
