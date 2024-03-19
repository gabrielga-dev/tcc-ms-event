package br.com.events.event.event.adapter.feing;

import br.com.events.event.event.data.io.outbound.msAuth.person.get_authenticated_person.out.GetAuthenticatedPersonInformationResult;

public interface PersonMsAuthFeign {

    GetAuthenticatedPersonInformationResult getAuthenticatedPersonInformation(String jwtToken);
}
