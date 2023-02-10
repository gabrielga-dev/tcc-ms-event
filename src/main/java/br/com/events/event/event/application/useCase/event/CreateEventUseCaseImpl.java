package br.com.events.event.event.application.useCase.event;

import org.springframework.stereotype.Component;

import br.com.events.event.event.domain.io.event.create.useCase.in.CreateEventUseCaseForm;
import br.com.events.event.event.domain.io.event.create.useCase.out.CreateEventUseCaseResult;
import br.com.events.event.event.domain.mapper.event.CreateEventMapper;
import br.com.events.event.event.domain.repository.EventRepository;
import br.com.events.event.event.infrastructure.useCase.event.CreateEventUseCase;
import br.com.events.event.event.infrastructure.validation.event.create.CreateEventValidator;
import lombok.RequiredArgsConstructor;

/**
 * This class implements {@link CreateEventUseCase} to create a new event
 *
 * @author Gabriel Guimarães de Almeida
 */
@Component
@RequiredArgsConstructor
public class CreateEventUseCaseImpl implements CreateEventUseCase {

    private final CreateEventValidator createEventValidator;
    private final EventRepository eventRepository;


    @Override
    public CreateEventUseCaseResult execute(final CreateEventUseCaseForm form) {
        createEventValidator.validate(form);

        var toSave = CreateEventMapper.toEntity(form);

        var saved = eventRepository.save(toSave);

        return CreateEventMapper.toUseCaseResult(saved);
    }
}
