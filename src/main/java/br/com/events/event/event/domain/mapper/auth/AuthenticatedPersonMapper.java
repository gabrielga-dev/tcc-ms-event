package br.com.events.event.event.domain.mapper.auth;

import br.com.events.event.event.domain.io.inbound.auth.AuthenticatedPerson;
import br.com.events.event.event.domain.io.outbound.msAuth.person.get_authenticated_person.out.GetAuthenticatedPersonInformationResult;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This class map every needed class at authentication needs
 *
 * @author Gabriel Guimarães de Almeida
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class AuthenticatedPersonMapper {

    /**
     * This method maps a {@link GetAuthenticatedPersonInformationResult} into a {@link AuthenticatedPerson} object
     *
     * @param result {@link GetAuthenticatedPersonInformationResult} object with the data to be mapped
     * @return {@link AuthenticatedPerson} object with the mapped information
     */
    public  static AuthenticatedPerson convertToAuthenticatedPerson(GetAuthenticatedPersonInformationResult result){
        return AuthenticatedPerson
            .builder()
            .uuid(result.getUuid())
            .firstName(result.getFirstName())
            .lastName(result.getLastName())
            .email(result.getEmail())
            .build();
    }
}
