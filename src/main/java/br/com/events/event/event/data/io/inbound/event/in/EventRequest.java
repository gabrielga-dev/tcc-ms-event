package br.com.events.event.event.data.io.inbound.event.in;

import br.com.events.event.event.data.io.inbound.address.in.AddressRequest;
import br.com.events.event.event.data.io.inbound.event.IEvent;
import br.com.events.event.event.core.util.DateUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class EventRequest implements IEvent {

    @NotNull(message = "O campo do nome de evento não pode ser nulo.")
    @NotBlank(message = "O campo do nome de evento não pode estar vazio.")
    @Size(min = 3, max = 100, message = "O campo do nome de evento deve conter, pelo menos, 1 caracteres e no máximo 100.")
    private String name;

    @NotNull(message = "O campo de descrição não pode ser nulo.")
    @NotBlank(message = "O campo de descrição não pode estar vazio.")
    @Size(min = 3, max = 100, message = "O campo de descrição deve conter, pelo menos, 10 caracteres e no máximo 500.")
    private String description;

    @Valid
    private AddressRequest address;

    @NotNull(message = "O campo de data do evento não pode ser nulo.")
    private Long dateTimestamp;

    public LocalDateTime getDate() {
        return DateUtil.timestampToLocalDateTime(dateTimestamp);
    }
}
