package br.com.events.event.event.domain.io.inbound.event.create.in;

import br.com.events.event.event.domain.io.inbound.event.create.base.BaseEventForm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.constraints.NotNull;

/**
 * This class holds every needed information for create a new event
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CreateEventRestForm extends BaseEventForm {

    @NotNull(message = "O campo de data do evento não pode ser nulo.")
    private Long dateTimestamp;
}
