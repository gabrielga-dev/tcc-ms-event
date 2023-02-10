package br.com.events.event.event.application.service;

import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.events.event.event.domain.io.service.location.in.CachedCountryStateCity;
import br.com.events.event.event.domain.mapper.service.location.CacheLocationMapper;
import br.com.events.event.event.infrastructure.feign.countryStateCity.CountryStateCityFeignClient;
import br.com.events.event.event.infrastructure.service.LocationService;
import lombok.RequiredArgsConstructor;

/**
 * This class implements the {@link LocationService} interface to work with all things related to event locations
 *
 * @author Gabriel Guimarãe de Almeida
 */
@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final CountryStateCityFeignClient countryStateCityFeignClient;

    @Override
    public Optional<CachedCountryStateCity> getCityByStateAndCountryIso2(
        final String countryIso2, final String stateIso2, final String city
    ) {
        var citiesResponse = countryStateCityFeignClient.getCitiesByStateAndCountryIso2(countryIso2, stateIso2);
        var cities = Objects.requireNonNull(citiesResponse.getBody());

        return cities.stream().filter(
                feignCity -> Objects.equals(city, feignCity.getName())
            ).findFirst()
            .map(
                CacheLocationMapper::toCached
            );
    }
}
