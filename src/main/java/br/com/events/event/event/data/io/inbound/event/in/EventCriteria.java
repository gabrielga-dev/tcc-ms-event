package br.com.events.event.event.data.io.inbound.event.in;

import br.com.events.event.event.core.util.DateUtil;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
public class EventCriteria {

    private String name;
    private Long startDate;
    private Long finalDate;
    private Boolean concluded;
    private String ownerUuid;

    public LocalDateTime getStartDate(){
        if (Objects.isNull(this.startDate)){
            return null;
        }
        return DateUtil.timestampToLocalDateTime(this.startDate);
    }

    public LocalDateTime getFinalDate(){
        if (Objects.isNull(this.finalDate)){
            return null;
        }
        return DateUtil.timestampToLocalDateTime(this.finalDate);
    }
}
