package br.com.events.event.event.clean.adapter.feing.client;

import br.com.events.event.event.clean.adapter.feing.PersonMsAuthFeign;
import br.com.events.event.event.clean.adapter.feing.client.config.MyEventFeignClientConfiguration;
import br.com.events.event.event.clean.data.io.outbound.msAuth.person.get_authenticated_person.out.GetAuthenticatedPersonInformationResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpHeaders;
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
public interface PersonMsAuthFeignClient extends PersonMsAuthFeign {

    @GetMapping("/v1/person")
    GetAuthenticatedPersonInformationResult getAuthenticatedPersonInformation(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String jwtToken
    );
}
