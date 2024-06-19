package br.com.events.event.event.core.exception.quote_request;

import br.com.events.event.event.core.exception.bad_request.NotFoundException;

public class QuoteRequestAlreadyAnsweredException extends NotFoundException {

    public QuoteRequestAlreadyAnsweredException() {
        super(
                "Pedido de orçamento já respondido!",
                "O pedido de orçamento em questão ja foi respondido."
        );
    }
}
