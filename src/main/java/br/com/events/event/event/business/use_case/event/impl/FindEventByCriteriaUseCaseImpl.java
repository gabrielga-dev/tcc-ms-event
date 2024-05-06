package br.com.events.event.event.business.use_case.event.impl;

import br.com.events.event.event.business.command.event.FindEventCommand;
import br.com.events.event.event.business.use_case.event.FindEventByCriteriaUseCase;
import br.com.events.event.event.core.util.AuthUtil;
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

    private final FindEventCommand findEventCommand;

    @Override
    public Page<EventResponse> execute(EventCriteria criteria, Pageable pageable) {
        if (Objects.isNull(AuthUtil.getAuthenticatedPerson())) {
            criteria.setOwnerUuid(null);
        }
        return findEventCommand.byCriteria(criteria, pageable)
                .map(EventResponse::new);
    }
}
