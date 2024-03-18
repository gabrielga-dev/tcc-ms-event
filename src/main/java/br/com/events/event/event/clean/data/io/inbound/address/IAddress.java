package br.com.events.event.event.clean.data.io.inbound.address;

public interface IAddress {

    String getStreet();
    String getNeighbour();
    Integer getNumber();
    String getComplement();

    Long getCityId();
    String getStateIso();
    String getCountryIso();

    String getZipCode();
}
