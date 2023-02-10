package br.com.events.event.event.domain.io.event.create.rest.in;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

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
public class CreateEventRestForm {

    @NotNull(message = "O campo do nome de evento não pode ser nulo.")
    @NotBlank(message = "O campo do nome de evento não pode estar vazio.")
    @Size(min = 3, max = 100, message = "O campo do nome de evento deve conter, pelo menos, 1 caracteres e no máximo 100.")
    private String name;

    @NotNull(message = "O campo de descrição não pode ser nulo.")
    @NotBlank(message = "O campo de descrição não pode estar vazio.")
    @Size(min = 3, max = 100, message = "O campo de descrição deve conter, pelo menos, 10 caracteres e no máximo 500.")
    private String description;

    @NotNull(message = "O campo de data do evento não pode ser nulo.")
    @NotBlank(message = "O campo de data do evento não pode estar vazio.")
    private Long dateTimestamp;

    @Valid
    private AddressCreateEventRestForm address;
}
