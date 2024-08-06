package br.com.events.event.event.data.io.outbound.msAuth.person.get_authenticated_person.out;

import br.com.events.event.event.MockConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class GetAuthenticatedPersonInformationResultMock {

    public static GetAuthenticatedPersonInformationResult build() {
        return GetAuthenticatedPersonInformationResult
                .builder()
                .uuid(MockConstants.STRING)
                .firstName(MockConstants.STRING)
                .lastName(MockConstants.STRING)
                .email(MockConstants.STRING)
                .cpf(MockConstants.STRING)
                .build();
    }
}
