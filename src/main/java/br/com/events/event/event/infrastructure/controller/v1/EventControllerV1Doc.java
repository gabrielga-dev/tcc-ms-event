package br.com.events.event.event.infrastructure.controller.v1;

import java.net.URI;

import org.springframework.http.ResponseEntity;

import br.com.events.event.event.domain.io.event.create.rest.in.CreateEventRestForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * This interface dictates which endpoints will be needed for implementation and holds which one's Swagger
 * documentation
 *
 * @author Gabriel Guimarães de Almeida
 */

@Api(tags = "Event Controller")
public interface EventControllerV1Doc {

    @ApiOperation(value = "Creates a basic version of a event")
    ResponseEntity<URI> create(CreateEventRestForm createEventRestForm);
}
