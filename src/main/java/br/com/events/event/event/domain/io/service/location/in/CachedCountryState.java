package br.com.events.event.event.domain.io.service.location.in;

import java.util.Map;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * This class holds every needed information to cache every state
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Builder
public class CachedCountryState {

    private Long id;
    private String name;
    private String iso2;
    private Map<String, CachedCountryStateCity> citiesMap;
}
