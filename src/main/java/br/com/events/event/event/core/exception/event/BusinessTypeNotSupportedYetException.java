package br.com.events.event.event.core.exception.event;

import br.com.events.event.event.core.exception.bad_request.BadRequestException;

public class BusinessTypeNotSupportedYetException extends BadRequestException {

    public BusinessTypeNotSupportedYetException(){
        super(
                "Tipo de serviço não suportado!",
                "Este tipo de serviço não é suportado, ainda, para criação de pedidos de orçamento."
        );
    }
}
