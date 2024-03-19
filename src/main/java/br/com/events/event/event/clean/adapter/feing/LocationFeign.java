package br.com.events.event.event.clean.adapter.feing;

public interface LocationFeign {

    void validateIfAddressExists(Long cityId, String stateIso, String countryIso);
}
