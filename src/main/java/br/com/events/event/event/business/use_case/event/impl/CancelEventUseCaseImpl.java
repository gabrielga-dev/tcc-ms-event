package br.com.events.event.event.business.use_case.event.impl;

import br.com.events.event.event.business.command.event.FindEventCommand;
import br.com.events.event.event.business.command.event.SaveEventCommand;
import br.com.events.event.event.business.use_case.event.CancelEventUseCase;
import br.com.events.event.event.core.exception.event.EventAlreadyHappenedException;
import br.com.events.event.event.core.exception.event.EventDoesNotExistsException;
import br.com.events.event.event.core.exception.event.NotEventOwnerException;
import br.com.events.event.event.core.util.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CancelEventUseCaseImpl implements CancelEventUseCase {

    private final FindEventCommand findEventCommand;
    private final SaveEventCommand saveEventCommand;

    @Override
    public void execute(String uuid) {
        var event = findEventCommand.byUuid(uuid).orElseThrow(EventDoesNotExistsException::new);

        if (event.alreadyHappened()) {
            throw new EventAlreadyHappenedException();
        }

        if (AuthUtil.getAuthenticatedPerson().getUuid().equals(event.getOwnerUuid())) {
            throw new NotEventOwnerException();
        }

        event.cancel();

        saveEventCommand.execute(event);
    }
}
