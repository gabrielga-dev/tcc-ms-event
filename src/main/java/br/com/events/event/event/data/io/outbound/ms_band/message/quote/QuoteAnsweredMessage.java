package br.com.events.event.event.data.io.outbound.ms_band.message.quote;

import br.com.events.event.event.data.model.type.BusinessType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuoteAnsweredMessage {

    private String msBandQuoteRequestUuid;
    private String msBandQuoteUuid;

    private BusinessType businessType;
    private String businessName;

    private String msEventQuoteUuid;
    private String msEventEventUuid;
    private BigDecimal price;
    private String observation;
}
