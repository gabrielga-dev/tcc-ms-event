package br.com.events.event.event.infrastructure.feign.msLocation;

import br.com.events.event.event.infrastructure.config.feign.MyEventFeignClientConfiguration;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * This interface communicates with MS-LOCATION
 *
 * @author Gabriel Guimarães de Almeida
 */
@FeignClient(
        name = "location-ms-location",
        url = "${feign.client.ms.location.url}",
        configuration = MyEventFeignClientConfiguration.class
)
public interface LocationFeignClient {

    @GetMapping("/v1/location/check-address")
    ResponseEntity<Void> validateIfAddressExists(
            @RequestParam("cityId") Long cityId,
            @RequestParam("stateIso") String stateIso,
            @RequestParam("countryIso") String countryIso
    );
}
