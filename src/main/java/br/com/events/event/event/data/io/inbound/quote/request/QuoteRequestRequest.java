package br.com.events.event.event.data.io.inbound.quote.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class QuoteRequestRequest {

    @Size(max = 500, message = "O campo de descrição deve ter, no máximo, 500 caracteres")
    private String description;

    @Valid
    private List<MusicQuoteRequestRequest> playlist;
    @Valid
    private List<MusicianTypeQuoteRequestRequest> musicianTypes;

}
