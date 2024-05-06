package br.com.events.event.event.data.model.type;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BusinessType {

    BAND("Bandas"),
    BUFFET("Buffet");

    private final String translatedName;
}
