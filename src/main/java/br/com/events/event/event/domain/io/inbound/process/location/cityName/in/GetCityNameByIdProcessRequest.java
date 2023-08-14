package br.com.events.event.event.domain.io.inbound.process.location.cityName.in;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Builder
public class GetCityNameByIdProcessRequest {

    private String countryIso;
    private String stateIso;
    private Long cityId;
}
