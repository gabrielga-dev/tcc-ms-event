package br.com.events.event.event.adapter.feing.client;

import br.com.events.event.event.adapter.feing.LocationFeign;
import br.com.events.event.event.adapter.feing.client.config.MyEventFeignClientConfiguration;
import br.com.events.event.event.data.io.outbound.msLocation.city.CityResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
public interface LocationFeignClient extends LocationFeign {

    @GetMapping("/v1/location/check-address")
    void validateIfAddressExists(
            @RequestParam("cityId") Long cityId,
            @RequestParam("stateIso") String stateIso,
            @RequestParam("countryIso") String countryIso
    );

    @GetMapping("/v1/location/country/{countryIso}/state/{stateIso}/city/{cityId}")
    CityResponse getCityByIdAndStateAndCountryIso(
            @PathVariable("countryIso") String countryIso,
            @PathVariable("stateIso") String stateIso,
            @PathVariable("cityId") Long cityId
    );
}
