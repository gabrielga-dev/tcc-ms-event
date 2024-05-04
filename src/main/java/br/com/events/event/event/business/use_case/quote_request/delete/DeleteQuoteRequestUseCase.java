package br.com.events.event.event.business.use_case.quote_request.delete;

import br.com.events.event.event.data.io.outbound.ms_band.message.quote_request.QuoteRequestCreationErrorMessage;

public interface DeleteQuoteRequestUseCase {

    void execute(QuoteRequestCreationErrorMessage object);
}
