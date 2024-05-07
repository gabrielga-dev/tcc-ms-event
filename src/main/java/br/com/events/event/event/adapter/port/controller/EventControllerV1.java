package br.com.events.event.event.adapter.port.controller;

import br.com.events.event.event.adapter.port.EventControllerV1Port;
import br.com.events.event.event.business.use_case.event.CancelEventUseCase;
import br.com.events.event.event.business.use_case.event.CreateEventUseCase;
import br.com.events.event.event.business.use_case.event.FindEventByCriteriaUseCase;
import br.com.events.event.event.business.use_case.event.FindEventNamesUseCase;
import br.com.events.event.event.business.use_case.event.FindEventProfileUseCase;
import br.com.events.event.event.data.io.inbound.event.in.EventCriteria;
import br.com.events.event.event.data.io.inbound.event.in.EventRequest;
import br.com.events.event.event.data.io.inbound.event.out.EventProfileResponse;
import br.com.events.event.event.data.io.inbound.event.out.EventResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

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
    private final FindEventByCriteriaUseCase findEventByCriteriaUseCase;
    private final FindEventProfileUseCase findEventProfileUseCase;
    private final CancelEventUseCase cancelEventUseCase;
    private final FindEventNamesUseCase findEventNamesUseCase;

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
        var resultPage = findEventByCriteriaUseCase.execute(criteria, pageable);
        return ResponseEntity.ok(resultPage);
    }

    @Override
    @GetMapping("/{uuid}")
    public ResponseEntity<EventProfileResponse> findByUuid(@PathVariable("uuid") String uuid) {
        var event = findEventProfileUseCase.execute(uuid);
        return ResponseEntity.ok(event);
    }

    @Override
    @GetMapping("/{uuid}/profile")
    public ResponseEntity<EventProfileResponse> findProfile(@PathVariable("uuid") String uuid) {
        var profile = findEventProfileUseCase.execute(uuid);
        return ResponseEntity.ok(profile);
    }

    @Override
    @DeleteMapping("/{uuid}/cancel")
    public ResponseEntity<Void> cancel(@PathVariable("uuid") String uuid) {
        cancelEventUseCase.execute(uuid);
        return ResponseEntity.noContent().build();
    }

    @Override
    @PreAuthorize("hasAnyAuthority('BAND', 'CONTRACTOR')")
    @GetMapping("/names")
    public ResponseEntity<Map<String, String>> findNames(@RequestParam List<String> eventUuids) {
        var names = findEventNamesUseCase.execute(eventUuids);
        return ResponseEntity.ok(names);
    }
}
