package br.com.events.event.event.business.command.event;

import br.com.events.event.event.business.command.address.CheckAddressCommand;
import br.com.events.event.event.core.exception.event.DateOnPastException;
import br.com.events.event.event.data.io.inbound.event.IEvent;
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
