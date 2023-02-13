package br.com.events.event.event.domain.io.event.create.useCase.out;

import java.time.LocalDateTime;

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
public class CreateEventUseCaseResult {

    private String uuid;
    private String name;
    private String description;
    private LocalDateTime date;
}
