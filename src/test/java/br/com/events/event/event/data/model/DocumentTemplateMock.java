package br.com.events.event.event.data.model;

import br.com.events.event.event.MockConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DocumentTemplateMock {

    public static DocumentTemplate build() {
        return new DocumentTemplate(
                MockConstants.LONG,
                MockConstants.STRING,
                MockConstants.STRING
        );
    }
}
