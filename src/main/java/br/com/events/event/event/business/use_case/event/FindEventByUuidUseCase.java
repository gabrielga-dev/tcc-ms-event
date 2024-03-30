package br.com.events.event.event.business.use_case.event;

import br.com.events.event.event.data.io.inbound.event.in.EventCriteria;
import br.com.events.event.event.data.io.inbound.event.out.EventResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FindEventByUuidUseCase {

    Page<EventResponse> execute(EventCriteria criteria, Pageable pageable);
}
