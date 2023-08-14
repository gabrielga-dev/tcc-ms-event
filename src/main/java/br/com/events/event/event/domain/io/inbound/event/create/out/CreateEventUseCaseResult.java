package br.com.events.event.event.domain.io.inbound.event.create.out;

import br.com.events.event.event.domain.io.inbound.event.create.base.BaseEventResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

/**
 * This class holds every generated data at event creation feature
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CreateEventUseCaseResult extends BaseEventResult {

    private LocalDateTime date;
}
