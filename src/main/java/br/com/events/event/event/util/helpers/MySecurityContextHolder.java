package br.com.events.event.event.util.helpers;

import br.com.events.event.event.domain.io.feign.msAuth.person.getAuthenticatedPerson.out.GetAuthenticatedPersonInformationResult;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * This class holds every needed information about jwt tokens at this MS
 *
 * @author Gabriel Guimarães de Almeida
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MySecurityContextHolder {

    private static String JWT_TOKEN;
    private static GetAuthenticatedPersonInformationResult AUTHENTICATED_PERSON;


    public static void clear(){
        JWT_TOKEN = null;
        AUTHENTICATED_PERSON = null;
    }

    public static void setContext(final String token, final GetAuthenticatedPersonInformationResult person) {
        JWT_TOKEN = token;
        AUTHENTICATED_PERSON = person;
    }

    public static String getJwtToken(){
        return JWT_TOKEN;
    }

    public static GetAuthenticatedPersonInformationResult getAuthenticatedPerson(){
        return AUTHENTICATED_PERSON;
    }
}
