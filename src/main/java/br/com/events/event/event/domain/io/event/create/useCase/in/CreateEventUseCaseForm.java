package br.com.events.event.event.domain.io.event.create.useCase.in;

import java.time.LocalDateTime;

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
public class CreateEventUseCaseForm {

    private String name;
    private String description;
    private LocalDateTime date;

    private AddressCreateEventUseCaseForm address;
}
