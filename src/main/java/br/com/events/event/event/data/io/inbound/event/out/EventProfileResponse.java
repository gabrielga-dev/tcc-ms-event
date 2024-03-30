package br.com.events.event.event.data.io.inbound.event.out;

import br.com.events.event.event.core.util.DateUtil;
import br.com.events.event.event.data.io.inbound.address.out.AddressResponse;
import br.com.events.event.event.data.model.Event;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EventProfileResponse {


    private String uuid;
    private String name;
    private String description;
    private Long dateTimeStamp;
    private AddressResponse address;

    public EventProfileResponse(Event event) {
        this.uuid = event.getUuid();
        this.name = event.getName();
        this.description = event.getDescription();
        this.dateTimeStamp = DateUtil.localDateTimeToTimestamp(event.getDate());
    }
}
