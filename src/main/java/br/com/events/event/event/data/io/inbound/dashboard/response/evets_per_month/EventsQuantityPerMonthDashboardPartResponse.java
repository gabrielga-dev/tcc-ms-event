package br.com.events.event.event.data.io.inbound.dashboard.response.evets_per_month;

import br.com.events.event.event.data.model.Event;
import lombok.Getter;
import lombok.Setter;

import java.text.DateFormatSymbols;
import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
@Setter
public class EventsQuantityPerMonthDashboardPartResponse {

    private final List<String> labels;
    private final String label;
    private final String backgroundColor = "#33cc33";
    private final List<EventsQuantityPerMonthDashboardPartDataResponse> data;



    public EventsQuantityPerMonthDashboardPartResponse(List<Event> quotes) {
        var portugueseMonths = new DateFormatSymbols(new Locale("pt", "BR")).getMonths();
        this.labels = Stream.of(Month.values())
                .map(month -> {
                    String nomeMes = portugueseMonths[month.getValue() - 1];
                    return nomeMes.substring(0, 1).toUpperCase() + nomeMes.substring(1).toLowerCase();
                })
                .collect(Collectors.toList());

        this.label = "Quantidade de eventos por mês";
        this.data = quotes.stream()
                .collect(
                        Collectors.groupingBy(
                                event -> event.getDate().getYear(),
                                HashMap::new,
                                Collectors.toList()
                        )
                ).entrySet().stream()
                .map(
                        entrySet -> new EventsQuantityPerMonthDashboardPartDataResponse(
                                entrySet.getKey(), entrySet.getValue()
                        )
                ).collect(Collectors.toList());
    }
}
