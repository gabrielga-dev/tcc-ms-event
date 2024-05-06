package br.com.events.event.event.business.use_case.event.impl;

import br.com.events.event.event.business.command.address.BuildAddressResponseCommand;
import br.com.events.event.event.business.command.event.FindEventCommand;
import br.com.events.event.event.business.use_case.event.FindEventProfileUseCase;
import br.com.events.event.event.core.exception.event.EventDoesNotExistsException;
import br.com.events.event.event.data.io.inbound.event.out.EventProfileResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindEventProfileUseCaseImpl implements FindEventProfileUseCase {

    private final FindEventCommand findEventCommand;
    private final BuildAddressResponseCommand buildAddressResponseCommand;

    @Override
    public EventProfileResponse execute(String uuid) {
        var event = findEventCommand.byUuid(uuid).orElseThrow(EventDoesNotExistsException::new);
        var profile = new EventProfileResponse(event);
        var address = buildAddressResponseCommand.execute(event.getAddress());
        profile.setAddress(address);

        return profile;
    }
}
