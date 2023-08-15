package br.com.events.event.event.domain.io.inbound.event.create.out;

import br.com.events.event.event.domain.io.inbound.event.create.base.BaseEventResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

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
public class CreateEventRestResult extends BaseEventResult {

    private String name;
    private String description;
    private Long dateTimestamp;
}
