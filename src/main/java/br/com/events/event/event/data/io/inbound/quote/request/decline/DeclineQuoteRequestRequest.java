package br.com.events.event.event.data.io.inbound.quote.request.decline;

import br.com.events.event.event.data.model.type.BusinessType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DeclineQuoteRequestRequest implements Serializable {

    @NotNull
    private String businessName;
    @NotNull
    private BusinessType businessType;
}
