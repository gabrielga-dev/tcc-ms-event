package br.com.events.event.event.business.use_case.quote.impl;

import br.com.events.event.event.business.command.event.FindEventCommand;
import br.com.events.event.event.business.service.AuthService;
import br.com.events.event.event.business.use_case.quote.GenerateDashboardUseCase;
import br.com.events.event.event.data.io.inbound.dashboard.response.DashboardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class GenerateDashboardUseCaseImpl implements GenerateDashboardUseCase {

    private final AuthService authService;
    private final FindEventCommand findEventCommand;

    @Override
    public DashboardResponse execute() {
        var allEvents = findEventCommand.findAll(authService.getAuthenticatedPerson().getUuid());

        return new DashboardResponse(allEvents);
    }
}
