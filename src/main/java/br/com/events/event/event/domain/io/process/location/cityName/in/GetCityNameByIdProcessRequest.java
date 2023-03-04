package br.com.events.event.event.domain.io.process.location.cityName.in;

import br.com.events.event.event.infrastructure.process.location.cityName.GetCityNameByIdProcess;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * This class holds all needed data at {@link GetCityNameByIdProcess}
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Builder
public class GetCityNameByIdProcessRequest {

    private String countryIso;
    private String stateIso;
    private Long cityId;
}
