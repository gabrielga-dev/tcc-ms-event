package br.com.events.event.event.adapter.feing;

public interface LocationFeign {

    void validateIfAddressExists(Long cityId, String stateIso, String countryIso);
}
