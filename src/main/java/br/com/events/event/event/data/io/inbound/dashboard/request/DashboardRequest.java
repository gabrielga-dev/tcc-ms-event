package br.com.events.event.event.data.io.inbound.dashboard.request;

import br.com.events.event.event.core.util.DateUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DashboardRequest {

    @NotNull(message = "Selecione alguma evento para gerar os gráficos")
    private String eventUuid;
    private Long startDate;
    private Long endDate;

    @JsonIgnore
    public LocalDateTime getStartDate() {
        return DateUtil.timestampToLocalDateTime(this.startDate);
    }

    @JsonIgnore
    public LocalDateTime getEndDate() {
        return DateUtil.timestampToLocalDateTime(this.endDate);
    }
}
