package br.com.events.event.event.data.io.inbound.dashboard.response.evets_per_month;

import br.com.events.event.event.data.model.Event;
import lombok.Getter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class EventsQuantityPerMonthDashboardPartDataResponse {

    private final Integer year;
    private final List<Long> values;

    public EventsQuantityPerMonthDashboardPartDataResponse(Integer year, List<Event> events) {
        this.year = year;

        this.values = new ArrayList<>(Arrays.asList(0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L, 0L));

        events.forEach(
                quote -> {
                    var month = quote.getDate().getMonthValue() - 1;
                    this.values.set(month, this.values.get(month) + 1);
                }
        );
    }
}
