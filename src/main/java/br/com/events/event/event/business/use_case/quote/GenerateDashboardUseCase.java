package br.com.events.event.event.business.use_case.quote;

import br.com.events.event.event.data.io.inbound.dashboard.response.DashboardResponse;

public interface GenerateDashboardUseCase {

    DashboardResponse execute();
}
