package br.com.events.event.event.infrastructure.process.location.cityName;

import br.com.events.event.event.domain.io.process.location.cityName.in.GetCityNameByIdProcessRequest;
import br.com.events.event.event.infrastructure.process.BaseProcess;

/**
 * This interfaces dictates that what is needed to get a city's name is a {@link GetCityNameByIdProcessRequest} object
 *
 * @author Gabriel Guiamrães de Almeida
 */
public interface GetCityNameByIdProcess extends BaseProcess<GetCityNameByIdProcessRequest, String> {
}
