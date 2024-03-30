package br.com.events.event.event.data.io.outbound.msLocation.city;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CityResponse {

    private Long id;
    private String name;
}
