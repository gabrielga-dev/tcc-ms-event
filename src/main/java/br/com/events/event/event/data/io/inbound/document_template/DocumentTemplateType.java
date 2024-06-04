package br.com.events.event.event.data.io.inbound.document_template;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DocumentTemplateType {

    BAND_CONTRACT(1L);

    private final Long id;
}
