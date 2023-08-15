package br.com.events.event.event.domain.io.inbound.event.create.in;

import br.com.events.event.event.domain.io.inbound.event.create.base.BaseEventForm;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

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
public class CreateEventUseCaseForm extends BaseEventForm {

    private LocalDateTime date;
}
