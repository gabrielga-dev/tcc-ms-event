package br.com.events.event.event.domain.io.event.create.useCase.in;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

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
    private Long cityId;
    private String stateIso;
    private String countryIso;
    private String zipCode;
    private BigDecimal latitude;
    private BigDecimal longitude;
}
