package br.com.events.event.event.business.use_case.quote_request.create.impl;

import br.com.events.event.event.business.use_case.quote_request.create.CreateQuoteRequestFactory;
import br.com.events.event.event.business.use_case.quote_request.create.CreateQuoteRequestUseCase;
import br.com.events.event.event.core.exception.event.BusinessTypeNotSupportedYetException;
import br.com.events.event.event.data.model.type.BusinessType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class CreateQuoteRequestFactoryImpl implements CreateQuoteRequestFactory {

    private final List<CreateQuoteRequestUseCase> quoteRequestUseCaseList;

    @Override
    public void execute(String eventUuid, BusinessType businessType, String businessUuid, Object quoteRequest) {
        this.quoteRequestUseCaseList
                .stream()
                .filter(useCase -> Objects.equals(useCase.getHandledBusinessType(), businessType))
                .findFirst()
                .orElseThrow(BusinessTypeNotSupportedYetException::new)
                .execute(eventUuid, businessUuid, quoteRequest);
    }
}
