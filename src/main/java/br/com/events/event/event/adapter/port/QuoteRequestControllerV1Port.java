package br.com.events.event.event.adapter.port;

import br.com.events.event.event.data.io.inbound.quote.request.QuoteRequestRequest;
import br.com.events.event.event.data.io.inbound.quote.response.QuoteRequestTypeResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.ResponseEntity;

import java.util.List;

/**
 * This interface dictates which endpoints will be needed for implementation and holds which one's Swagger
 * documentation
 *
 * @author Gabriel Guimarães de Almeida
 */
@Api(tags = "Quote Controller")
public interface QuoteRequestControllerV1Port {

    @ApiOperation(value = "List all quotes for")
    ResponseEntity<List<QuoteRequestTypeResponse>> findAll(String eventUuid);

    @ApiOperation(value = "Creates a new quote request for a band")
    ResponseEntity<Void> createForBand(String eventUuid, String bandUuid, QuoteRequestRequest quoteRequest);
}
