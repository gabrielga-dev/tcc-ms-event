package br.com.events.event.event.adapter.port;

import br.com.events.event.event.data.io.inbound.event.in.EventCriteria;
import br.com.events.event.event.data.io.inbound.event.in.EventRequest;
import br.com.events.event.event.data.io.inbound.event.out.EventProfileResponse;
import br.com.events.event.event.data.io.inbound.event.out.EventResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    @ApiOperation(value = "Find events by criteria")
    ResponseEntity<Page<EventResponse>> findByCriteria(EventCriteria criteria, Pageable pageable);

    @ApiOperation(value = "Find an event's profile by it's uuid")
    ResponseEntity<EventProfileResponse> findProfile(String uuid);
}
