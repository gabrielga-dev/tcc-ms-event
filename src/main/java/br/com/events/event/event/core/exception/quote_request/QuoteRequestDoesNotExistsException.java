package br.com.events.event.event.core.exception.quote_request;

import br.com.events.event.event.core.exception.bad_request.NotFoundException;

public class QuoteRequestDoesNotExistsException extends NotFoundException {

    public QuoteRequestDoesNotExistsException() {
        super(
                "Pedido de orçamento não encontrado!",
                "Por favor, insira um identificador de um pedido de orçamento cadastrado na plataforma."
        );
    }
}
