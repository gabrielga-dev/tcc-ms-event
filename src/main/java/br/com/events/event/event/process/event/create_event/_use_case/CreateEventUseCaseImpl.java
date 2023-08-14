package br.com.events.event.event.process.event.create_event._use_case;

import br.com.events.event.event.domain.io.inbound.event.create.in.CreateEventUseCaseForm;
import br.com.events.event.event.domain.io.inbound.event.create.out.CreateEventUseCaseResult;
import br.com.events.event.event.domain.mapper.event.CreateEventMapper;
import br.com.events.event.event.domain.repository.EventRepository;
import br.com.events.event.event.process.event.create_event._use_case.interfaces.CreateEventUseCase;
import br.com.events.event.event.process.event.create_event.validations.CreateEventValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

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
