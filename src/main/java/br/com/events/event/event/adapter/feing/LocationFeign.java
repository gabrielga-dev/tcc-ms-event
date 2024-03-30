package br.com.events.event.event.adapter.feing;

import br.com.events.event.event.data.io.outbound.msLocation.city.CityResponse;

public interface LocationFeign {

    void validateIfAddressExists(Long cityId, String stateIso, String countryIso);

    CityResponse getCityByIdAndStateAndCountryIso(String countryIso, String stateIso, Long cityId);
}
