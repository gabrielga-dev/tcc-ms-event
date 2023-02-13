package br.com.events.event.event.infrastructure.service;

import java.util.Optional;

import br.com.events.event.event.domain.io.service.location.in.CachedCountryStateCity;

/**
 * This interface dictates every needed method to work with location
 *
 * @author Gabriel Guimarães de Almeida
 */
public interface LocationService {

    /**
     * This method checks if the given city belongs to the given state and the given state belongs to the given country
     *
     * @param countryIso2 {@link String} country's initials
     * @param stateIso2 {@link String} state's initials
     * @param city {@link String} city name
     * @return boolean flag: true -> it exists; false -> it does not exist
     */
    Optional<CachedCountryStateCity> getCityByStateAndCountryIso2(String countryIso2, String stateIso2, String city);
}
