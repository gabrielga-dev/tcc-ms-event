package br.com.events.event.event.business.command.person.impl;

import br.com.events.event.event.adapter.feing.PersonMsAuthFeign;
import br.com.events.event.event.business.command.person.FindPersonCommand;
import br.com.events.event.event.data.io.outbound.msAuth.person.findByUuid.out.PersonResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindPersonCommandImpl implements FindPersonCommand {

    private final PersonMsAuthFeign personMsAuthFeign;

    @Override
    public PersonResponse execute(String uuid) {
        return personMsAuthFeign.findPersonByUuid(uuid);
    }
}
