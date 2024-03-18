package br.com.events.event.event.clean.business.command.band;

import br.com.events.event.event.clean.business.command.address.CheckAddressCommand;
import br.com.events.event.event.clean.core.exception.event.DateOnPastException;
import br.com.events.event.event.clean.data.io.inbound.event.IEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class CheckEventCommand {

    private final CheckAddressCommand checkAddressCommand;

    public void execute(IEvent event) {
        if (event.getDate().isBefore(LocalDateTime.now())) {
            throw new DateOnPastException();
        }
        checkAddressCommand.execute(event.getAddress());
    }
}
