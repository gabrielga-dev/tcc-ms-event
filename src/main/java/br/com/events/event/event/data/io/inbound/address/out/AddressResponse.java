package br.com.events.event.event.data.io.inbound.address.out;

import br.com.events.event.event.data.io.outbound.msLocation.city.CityResponse;
import br.com.events.event.event.data.model.Address;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class AddressResponse {

    private String street;
    private String neighbour;
    private Integer number;
    private String complement;
    private String city;
    private Long cityId;
    private String state;
    private String country;
    private String zipCode;
    private BigDecimal latitude;
    private BigDecimal longitude;

    public AddressResponse(Address address, CityResponse city) {
        this.street = address.getStreet();
        this.neighbour = address.getNeighbour();
        this.number = address.getNumber();
        this.complement = address.getComplement();
        this.city = city.getName();
        this.cityId = city.getId();
        this.state = address.getState();
        this.country = address.getCountry();
        this.zipCode = address.getZipCode();
    }
}
