package br.com.events.event.event.business.use_case.quote;

public interface AnswerQuoteUseCase {

    void execute(String quoteRequestUuid, boolean hired);
}
