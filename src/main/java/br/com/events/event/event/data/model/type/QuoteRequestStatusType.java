package br.com.events.event.event.data.model.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum QuoteRequestStatusType {

    NON_ANSWERED("Não respondido"),
    ANSWERED("Respondido"),
    DECLINED("Negado"),
    HIRED("Contratado");

    private final String translatedName;
}
