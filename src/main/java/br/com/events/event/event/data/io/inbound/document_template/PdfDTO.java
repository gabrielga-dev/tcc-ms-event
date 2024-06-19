package br.com.events.event.event.data.io.inbound.document_template;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PdfDTO {

    private final String fileName;
    private final byte[] fileBytes;
}
