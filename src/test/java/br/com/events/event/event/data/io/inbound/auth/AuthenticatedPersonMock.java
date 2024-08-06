package br.com.events.event.event.data.io.inbound.auth;

import br.com.events.event.event.data.io.outbound.msAuth.person.get_authenticated_person.out.GetAuthenticatedPersonInformationResultMock;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AuthenticatedPersonMock {

    public static AuthenticatedPerson build() {
        return new AuthenticatedPerson(GetAuthenticatedPersonInformationResultMock.build());
    }
}
