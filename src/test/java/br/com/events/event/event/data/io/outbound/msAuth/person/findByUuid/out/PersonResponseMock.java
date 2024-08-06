package br.com.events.event.event.data.io.outbound.msAuth.person.findByUuid.out;

import br.com.events.event.event.MockConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PersonResponseMock {

    public static PersonResponse build() {
        var toReturn = new PersonResponse();

        toReturn.setUuid(MockConstants.STRING);
        toReturn.setFirstName(MockConstants.STRING);
        toReturn.setLastName(MockConstants.STRING);
        toReturn.setEmail(MockConstants.STRING);
        toReturn.setCpf(MockConstants.STRING);

        return toReturn;
    }
}
