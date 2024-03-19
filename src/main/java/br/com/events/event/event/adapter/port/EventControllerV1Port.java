package br.com.events.event.event.adapter.port;

import br.com.events.event.event.data.io.inbound.event.in.EventRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

import java.net.URI;

/**
 * This interface dictates which endpoints will be needed for implementation and holds which one's Swagger
 * documentation
 *
 * @author Gabriel Guimarães de Almeida
 */

@Api(tags = "Event Controller")
public interface EventControllerV1Port {

    @ApiOperation(value = "Creates a basic version of a event")
    ResponseEntity<URI> create(EventRequest request);
}
