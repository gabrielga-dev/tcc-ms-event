package br.com.events.event.event.business.command.quote_request;

import br.com.events.event.event.adapter.repository.QuoteRequestRepository;
import br.com.events.event.event.data.model.QuoteRequest;
import br.com.events.event.event.data.model.QuoteRequestMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link FindQuoteRequestCommand}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class SaveQuoteRequestCommandTest {

    @InjectMocks
    private SaveQuoteRequestCommand command;

    @Mock
    private QuoteRequestRepository quoteRequestRepository;

    @Test
    @DisplayName("execute - when called return saved quote request")
    void executeWhenCalledReturnSavedQuoteRequest() {
        var saved = QuoteRequestMock.build();
        when(quoteRequestRepository.save(any(QuoteRequest.class))).thenReturn(saved);

        var toSave = QuoteRequestMock.build();
        var returned = command.execute(toSave);

        Assertions.assertNotNull(returned);
        Assertions.assertEquals(saved, returned);

        verify(quoteRequestRepository, times(1)).save(any(QuoteRequest.class));
    }
}
