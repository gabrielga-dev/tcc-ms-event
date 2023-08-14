package br.com.events.event.event.infrastructure.feign.msAuth;

import br.com.events.event.event.infrastructure.config.feign.MyEventFeignClientConfiguration;
import br.com.events.event.event.domain.io.outbound.msAuth.person.get_authenticated_person.out.GetAuthenticatedPersonInformationResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

/**
 * This interface communicates with MS-AUTH
 *
 * @author Gabriel Guimarães de Almeida
 */
@FeignClient(
    name = "person-ms-auth",
    url = "${feign.client.ms.auth.url}",
    configuration = MyEventFeignClientConfiguration.class
)
public interface PersonMsAuthFeignClient {

    @GetMapping("/v1/person")
    ResponseEntity<GetAuthenticatedPersonInformationResult> getAuthenticatedPersonInformation(
        @RequestHeader(HttpHeaders.AUTHORIZATION) String jwtToken
    );
}
