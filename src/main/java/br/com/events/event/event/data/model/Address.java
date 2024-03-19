package br.com.events.event.event.data.model;

import br.com.events.event.event.data.io.inbound.address.in.AddressRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

/**
 * This class represents the event address's database table
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "address")
public class Address {

    @Id
    @Column(name = "event_uuid", nullable = false)
    private String eventUuid;

    @Column(name = "street", nullable = false)
    private String street;

    @Column(name = "neighbour", nullable = false)
    private String neighbour;

    @Column(name = "complement")
    private String complement;

    @Column(name = "city", nullable = false)
    private Long city;

    @Column(name = "state", nullable = false)
    private String state;

    @Column(name = "country", nullable = false)
    private String country;

    @Column(name = "zip_code", nullable = false)
    private String zipCode;

    @Column(name = "latitude", nullable = false)
    private BigDecimal latitude;

    @Column(name = "longitude", nullable = false)
    private BigDecimal longitude;

    @OneToOne
    @JoinColumn(name = "event_uuid", referencedColumnName = "uuid")
    private Event event;

    public Address(AddressRequest form) {
        this.street = form.getStreet();
        this.neighbour = form.getNeighbour();
        this.complement = form.getComplement();
        this.city = form.getCityId();
        this.state = form.getStateIso();
        this.country = form.getCountryIso();
        this.zipCode = form.getZipCode();
        this.latitude = form.getLatitude();
        this.longitude = form.getLongitude();
    }
}
