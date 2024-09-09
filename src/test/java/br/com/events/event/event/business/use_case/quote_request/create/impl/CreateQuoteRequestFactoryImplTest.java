package br.com.events.event.event.business.use_case.quote_request.create.impl;

import br.com.events.event.event.MockConstants;
import br.com.events.event.event.business.use_case.quote_request.create.CreateQuoteRequestUseCase;
import br.com.events.event.event.core.exception.event.BusinessTypeNotSupportedYetException;
import br.com.events.event.event.data.io.inbound.quote.request.create.QuoteRequestRequest;
import br.com.events.event.event.data.io.inbound.quote.request.create.QuoteRequestRequestMock;
import br.com.events.event.event.data.model.type.BusinessType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link CreateQuoteRequestFactoryImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class CreateQuoteRequestFactoryImplTest {

    @Test
    @DisplayName("execute - when no use case matches to filter, then throw BusinessTypeNotSupportedYetException")
    void executeWhenNoUseCaseMatchesToFilterThenThrowBusinessTypeNotSupportedYetException() {
        var mockedUseCase1 = mock(CreateQuoteRequestUseCase.class);
        when(mockedUseCase1.getHandledBusinessType()).thenReturn(BusinessType.BUFFET);

        var mockedUseCase2 = mock(CreateQuoteRequestUseCase.class);
        when(mockedUseCase2.getHandledBusinessType()).thenReturn(BusinessType.BUFFET);

        final CreateQuoteRequestFactoryImpl factory = new CreateQuoteRequestFactoryImpl(
                List.of(mockedUseCase1, mockedUseCase2)
        );

        var request = QuoteRequestRequestMock.build();
        Assertions.assertThrows(
                BusinessTypeNotSupportedYetException.class,
                () -> factory.execute(MockConstants.STRING, BusinessType.BAND, MockConstants.STRING, request)
        );

        verify(mockedUseCase1, times(1)).getHandledBusinessType();
        verify(mockedUseCase1, times(0)).execute(anyString(), anyString(), any(QuoteRequestRequest.class));
        verify(mockedUseCase2, times(1)).getHandledBusinessType();
        verify(mockedUseCase2, times(0)).execute(anyString(), anyString(), any(QuoteRequestRequest.class));
    }

    @Test
    @DisplayName("execute - when a use case matches to filter, then call execute method")
    void executeWhenUseCaseMatchesToFilterThenCallExecuteMethod() {
        var mockedUseCase1 = mock(CreateQuoteRequestUseCase.class);
        when(mockedUseCase1.getHandledBusinessType()).thenReturn(BusinessType.BUFFET);

        var mockedUseCase2 = mock(CreateQuoteRequestUseCase.class);
        when(mockedUseCase2.getHandledBusinessType()).thenReturn(BusinessType.BAND);
        doNothing().when(mockedUseCase2).execute(anyString(), anyString(), any(QuoteRequestRequest.class));

        final CreateQuoteRequestFactoryImpl factory = new CreateQuoteRequestFactoryImpl(
                List.of(mockedUseCase1, mockedUseCase2)
        );

        var request = QuoteRequestRequestMock.build();
        Assertions.assertDoesNotThrow(
                () -> factory.execute(MockConstants.STRING, BusinessType.BAND, MockConstants.STRING, request)
        );

        verify(mockedUseCase1, times(1)).getHandledBusinessType();
        verify(mockedUseCase1, times(0)).execute(anyString(), anyString(), any(QuoteRequestRequest.class));
        verify(mockedUseCase2, times(1)).getHandledBusinessType();
        verify(mockedUseCase2, times(1)).execute(anyString(), anyString(), any(QuoteRequestRequest.class));
    }
}