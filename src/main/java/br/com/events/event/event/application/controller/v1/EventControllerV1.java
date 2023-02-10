package br.com.events.event.event.application.controller.v1;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.events.event.event.domain.io.event.create.rest.in.CreateEventRestForm;
import br.com.events.event.event.domain.mapper.event.CreateEventMapper;
import br.com.events.event.event.infrastructure.controller.v1.EventControllerV1Doc;
import br.com.events.event.event.infrastructure.useCase.event.CreateEventUseCase;
import lombok.RequiredArgsConstructor;

/**
 * This class implements every needed endpoint that is related to event
 *
 * @author Gabriel Guimarães de Almeida
 */
@RestController
@RequestMapping("/v1/event")
@RequiredArgsConstructor
public class EventControllerV1 implements EventControllerV1Doc {

    private final CreateEventUseCase createEventUseCase;

    @Override
    @PostMapping
    public ResponseEntity<URI> create(@RequestBody @Valid CreateEventRestForm createEventRestForm) {
        var mappedForm = CreateEventMapper.toUseCaseForm(createEventRestForm);

        var result = createEventUseCase.execute(mappedForm);
        var mappedResult = CreateEventMapper.toRestResult(result);

        var uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{uuid}").buildAndExpand(mappedResult).toUri();
        return ResponseEntity.created(uri).build();
    }
}
