package br.com.events.event.event.data.io.inbound.address;

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
