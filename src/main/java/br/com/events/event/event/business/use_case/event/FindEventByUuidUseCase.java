package br.com.events.event.event.business.use_case.event;

import br.com.events.event.event.data.io.inbound.event.out.EventResponse;

public interface FindEventByUuidUseCase {

    EventResponse execute(String eventUuid);
}
