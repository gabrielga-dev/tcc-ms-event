package br.com.events.event.event.business.command.person;

import br.com.events.event.event.data.io.outbound.msAuth.person.findByUuid.out.PersonResponse;

public interface FindPersonCommand {

    PersonResponse execute(String uuid);
}
