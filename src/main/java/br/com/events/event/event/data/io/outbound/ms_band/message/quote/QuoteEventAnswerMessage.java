package br.com.events.event.event.data.io.outbound.ms_band.message.quote;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuoteEventAnswerMessage {

    private String quoteUuid;
    private boolean hired;
}
