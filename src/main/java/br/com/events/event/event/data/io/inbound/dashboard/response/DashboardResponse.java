package br.com.events.event.event.data.io.inbound.dashboard.response;

import br.com.events.event.event.data.io.inbound.dashboard.response.evets_per_month.EventsQuantityPerMonthDashboardPartResponse;
import br.com.events.event.event.data.io.inbound.dashboard.response.quote_request_statuses.QuoteRequestStatusesDashboardPartResponse;
import br.com.events.event.event.data.io.inbound.event.out.EventResponse;
import br.com.events.event.event.data.model.Event;
import lombok.Getter;
import lombok.Setter;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class DashboardResponse {

    private final List<EventResponse> nextEvents;
    private final EventsQuantityPerMonthDashboardPartResponse eventQuantityPerMonth;
    private final QuoteRequestStatusesDashboardPartResponse quoteRequestStatuses;


    public DashboardResponse(List<Event> allEvents) {
        this.nextEvents = allEvents.parallelStream()
                .filter(event -> event.getActive() && !event.alreadyHappened())
                .sorted(Comparator.comparing(Event::getDate))
                .map(EventResponse::new)
                .collect(Collectors.toList());

        this.eventQuantityPerMonth = new EventsQuantityPerMonthDashboardPartResponse(allEvents);
        this.quoteRequestStatuses = new QuoteRequestStatusesDashboardPartResponse(
                allEvents.parallelStream()
                        .filter(event -> event.getActive() && !event.alreadyHappened())
                        .flatMap(event -> event.getQuotes().stream())
                        .collect(Collectors.toList())
        );
    }
}
