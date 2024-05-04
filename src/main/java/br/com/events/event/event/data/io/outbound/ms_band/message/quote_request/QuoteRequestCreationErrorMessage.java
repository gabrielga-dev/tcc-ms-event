package br.com.events.event.event.data.io.outbound.ms_band.message.quote_request;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class QuoteRequestCreationErrorMessage {

    private final String quoteRequestUuid;
}
