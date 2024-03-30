package br.com.events.event.event.adapter.port.controller;

import br.com.events.event.event.adapter.port.EventControllerV1Port;
import br.com.events.event.event.business.use_case.event.CreateEventUseCase;
import br.com.events.event.event.business.use_case.event.FindEventByUuidUseCase;
import br.com.events.event.event.business.use_case.event.FindEventProfileUseCase;
import br.com.events.event.event.data.io.inbound.event.in.EventCriteria;
import br.com.events.event.event.data.io.inbound.event.in.EventRequest;
import br.com.events.event.event.data.io.inbound.event.out.EventProfileResponse;
import br.com.events.event.event.data.io.inbound.event.out.EventResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    private final FindEventByUuidUseCase findEventByUuidUseCase;
    private final FindEventProfileUseCase findEventProfileUseCase;

    @Override
    @PostMapping
    public ResponseEntity<URI> create(@RequestBody EventRequest request) {
        var result = createEventUseCase.execute(request);

        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{uuid}").buildAndExpand(result).toUri();
        return ResponseEntity.created(uri).build();
    }

    @Override
    @GetMapping
    public ResponseEntity<Page<EventResponse>> findByCriteria(EventCriteria criteria, Pageable pageable) {
        var resultPage = findEventByUuidUseCase.execute(criteria, pageable);
        return ResponseEntity.ok(resultPage);
    }

    @Override
    @GetMapping("/{uuid}/profile")
    public ResponseEntity<EventProfileResponse> findProfile(@PathVariable("uuid") String uuid) {
        var profile = findEventProfileUseCase.execute(uuid);
        return ResponseEntity.ok(profile);
    }
}
