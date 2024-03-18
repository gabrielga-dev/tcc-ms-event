package br.com.events.event.event.clean.adapter.feing;

import br.com.events.event.event.clean.data.io.outbound.msAuth.person.get_authenticated_person.out.GetAuthenticatedPersonInformationResult;

public interface PersonMsAuthFeign {

    GetAuthenticatedPersonInformationResult getAuthenticatedPersonInformation(String jwtToken);
}
