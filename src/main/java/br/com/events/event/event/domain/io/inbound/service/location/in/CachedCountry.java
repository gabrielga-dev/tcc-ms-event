package br.com.events.event.event.domain.io.inbound.service.location.in;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * This class holds every needed information to cache every country
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Builder
public class CachedCountry {

    private Long id;
    private String name;
    private String iso2;

    private Map<String, CachedCountryState> statesMap;
}
