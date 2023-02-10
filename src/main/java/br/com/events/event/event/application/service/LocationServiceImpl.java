package br.com.events.event.event.application.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.events.event.event.domain.io.service.location.in.CachedCountry;
import br.com.events.event.event.domain.io.service.location.in.CachedCountryState;
import br.com.events.event.event.domain.io.service.location.in.CachedCountryStateCity;
import br.com.events.event.event.domain.mapper.service.location.CacheLocationMapper;
import br.com.events.event.event.infrastructure.feign.CountryStateCityFeignClient;
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

    private final Map<String, CachedCountry> cachedCountryMap = new HashMap<>();

    @Override
    public Optional<CachedCountryStateCity> getCityByStateAndCountryIso2(
        final String countryIso2, final String stateIso2, final String city
    ) {
        if (cachedCountryMap.isEmpty()) {
            populateCountryMap();
        }

        var countryOpt = Optional.ofNullable(cachedCountryMap.get(countryIso2));

        if (countryOpt.isPresent()){
            var stateOpt = Optional.ofNullable(countryOpt.get().getStatesMap().get(stateIso2));

            if (stateOpt.isPresent()){
                return Optional.ofNullable(stateOpt.get().getCitiesMap().get(city));
            }
        }

        return Optional.empty();
    }

    private void populateCountryMap() {
        var getCountriesResult = Objects.requireNonNull(countryStateCityFeignClient.getCountries().getBody());

        getCountriesResult
            .stream().map(CacheLocationMapper::toCached)
            .forEach(
                country -> {
                    country.setStatesMap(generateStateMap(country.getIso2()));
                    cachedCountryMap.put(country.getIso2(), country);
                }
            );
    }

    private Map<String, CachedCountryState> generateStateMap(final String countryIso2) {
        var toReturn = new HashMap<String, CachedCountryState>();

        var getStatesByCountryResult = Objects.requireNonNull(
            countryStateCityFeignClient.getStatesByCountryIso2(countryIso2).getBody()
        );

        getStatesByCountryResult
            .stream().map(CacheLocationMapper::toCached)
            .forEach(
                state -> {
                    state.setCitiesMap(generateCitiesMap(countryIso2, state.getIso2()));
                    toReturn.put(state.getIso2(), state);
                }
            );

        return toReturn;
    }

    private Map<String, CachedCountryStateCity> generateCitiesMap(final String countryIso2, final String stateIso2) {
        var toReturn = new HashMap<String, CachedCountryStateCity>();

        var getStatesByCountryResult = Objects.requireNonNull(
            countryStateCityFeignClient.getCitiesByStateAndCountryIso2(countryIso2, stateIso2).getBody()
        );

        getStatesByCountryResult
            .stream().map(CacheLocationMapper::toCached)
            .forEach(
                city -> toReturn.put(city.getName(), city)
            );

        return toReturn;
    }
}
