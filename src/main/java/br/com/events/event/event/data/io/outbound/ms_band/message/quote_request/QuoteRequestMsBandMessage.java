package br.com.events.event.event.data.io.outbound.ms_band.message.quote_request;

import br.com.events.event.event.data.io.inbound.quote.request.MusicQuoteRequestRequest;
import br.com.events.event.event.data.io.inbound.quote.request.MusicianTypeQuoteRequestRequest;
import br.com.events.event.event.data.io.inbound.quote.request.QuoteRequestRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class QuoteRequestMsBandMessage implements Serializable {

    private String eventUuid;
    private String quoteRequestUuid;
    private String bandUuid;
    private String description;
    private List<MusicQuoteRequestRequest> playlist = new ArrayList<>();
    private List<MusicianTypeQuoteRequestRequest> musicianTypes = new ArrayList<>();

    public QuoteRequestMsBandMessage(
            String eventUuid,
            String quoteRequestUuid,
            String bandUuid,
            QuoteRequestRequest quoteRequest
    ) {
        this.eventUuid = eventUuid;
        this.quoteRequestUuid = quoteRequestUuid;
        this.bandUuid = bandUuid;
        this.description = quoteRequest.getDescription();
        this.playlist = quoteRequest.getPlaylist();
        this.musicianTypes = quoteRequest.getMusicianTypes();
    }
}
