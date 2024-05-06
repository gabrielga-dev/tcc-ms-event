package br.com.events.event.event.business.use_case.event;

import br.com.events.event.event.data.io.inbound.event.out.EventProfileResponse;

public interface FindEventProfileUseCase {

    EventProfileResponse execute(String uuid);
}
