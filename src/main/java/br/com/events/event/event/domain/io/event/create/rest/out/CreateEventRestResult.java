package br.com.events.event.event.domain.io.event.create.rest.out;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * This class holds every generated data at event creation feature
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Builder
public class CreateEventRestResult {

    private String name;
    private String description;
    private Long dateTimestamp;
}
