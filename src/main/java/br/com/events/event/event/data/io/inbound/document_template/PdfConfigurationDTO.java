package br.com.events.event.event.data.io.inbound.document_template;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PdfConfigurationDTO {

    private final String template;
    private final boolean waterMark = true;
}
