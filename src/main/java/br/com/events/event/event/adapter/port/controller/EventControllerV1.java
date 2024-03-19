package br.com.events.event.event.adapter.port.controller;

import br.com.events.event.event.adapter.port.EventControllerV1Port;
import br.com.events.event.event.business.use_case.event.CreateEventUseCase;
import br.com.events.event.event.data.io.inbound.event.in.EventRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

/**
 * This class implements every needed endpoint that is related to event
 *
 * @author Gabriel Guimarães de Almeida
 */
@RestController
@RequestMapping("/v1/event")
@RequiredArgsConstructor
public class EventControllerV1 implements EventControllerV1Port {

    private final CreateEventUseCase createEventUseCase;

    @Override
    @PostMapping
    public ResponseEntity<URI> create(@RequestBody EventRequest request) {
        var result = createEventUseCase.execute(request);

        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{uuid}").buildAndExpand(result).toUri();
        return ResponseEntity.created(uri).build();
    }
}
