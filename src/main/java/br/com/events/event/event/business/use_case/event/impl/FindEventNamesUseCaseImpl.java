package br.com.events.event.event.business.use_case.event.impl;

import br.com.events.event.event.business.command.event.FindEventCommand;
import br.com.events.event.event.business.use_case.event.FindEventNamesUseCase;
import br.com.events.event.event.data.model.Event;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class FindEventNamesUseCaseImpl implements FindEventNamesUseCase {

    private final FindEventCommand findEventCommand;

    @Override
    public Map<String, String> execute(List<String> uuids) {
        return findEventCommand.byUuids(uuids)
                .stream()
                .collect(
                        Collectors.toMap(
                                Event::getUuid,
                                Event::getName
                        )
                );
    }
}
