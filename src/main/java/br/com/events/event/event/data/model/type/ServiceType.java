package br.com.events.event.event.data.model.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ServiceType {

    BAND("Bandas"),
    BUFFET("Buffet");

    private final String translatedName;
}
