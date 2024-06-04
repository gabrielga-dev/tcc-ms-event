package br.com.events.event.event.core.exception.document_template;

import br.com.events.event.event.core.exception.internal_server_error.ServerSideException;

public class CouldNotGenerateDocumentException extends ServerSideException {
    public CouldNotGenerateDocumentException() {
        super(
                "Não foi possível gerar o documento!",
                "Algo ocorreu e impediu que nós conseguíssemos gerar o documento requerido!"
        );
    }

    public CouldNotGenerateDocumentException(Exception e) {
        super(
                "Não foi possível gerar o documento!",
                "Algo ocorreu e impediu que nós conseguíssemos gerar o documento requerido! " + e.getLocalizedMessage()
        );
    }
}
