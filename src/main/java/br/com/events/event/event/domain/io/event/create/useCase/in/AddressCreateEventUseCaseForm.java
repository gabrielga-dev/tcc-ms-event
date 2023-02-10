package br.com.events.event.event.domain.io.event.create.useCase.in;

import java.math.BigDecimal;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * This class holds every needed information for create a new event
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Builder
public class AddressCreateEventUseCaseForm {

    private String street;
    private String neighbour;
    private String complement;
    private String city;
    private String state;
    private String country;
    private String zipCode;
    private BigDecimal latitude;
    private BigDecimal longitude;
}
