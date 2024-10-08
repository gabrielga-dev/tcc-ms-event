package br.com.events.event.event.data.io.inbound.event.out;

import br.com.events.event.event.core.util.DateUtil;
import br.com.events.event.event.data.model.Event;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventResponse {

    private String uuid;
    private String name;
    private String description;
    private Long dateTimestamp;
    private boolean active;

    public EventResponse(Event event) {
        this.uuid = event.getUuid();
        this.name = event.getName();
        this.description = event.getDescription();
        this.dateTimestamp = DateUtil.localDateTimeToTimestamp(event.getDate());
        this.active = event.getActive() && !event.alreadyHappened();
    }
}
