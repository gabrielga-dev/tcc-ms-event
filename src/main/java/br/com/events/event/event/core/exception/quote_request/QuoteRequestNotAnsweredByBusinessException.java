package br.com.events.event.event.core.exception.quote_request;

import br.com.events.event.event.core.exception.bad_request.NotFoundException;

public class QuoteRequestNotAnsweredByBusinessException extends NotFoundException {

    public QuoteRequestNotAnsweredByBusinessException() {
        super(
                "Pedido de orçamento não respondido!",
                "O pedido de orçamento em questão não foi respondido pelo prestador de serviço."
        );
    }
}
