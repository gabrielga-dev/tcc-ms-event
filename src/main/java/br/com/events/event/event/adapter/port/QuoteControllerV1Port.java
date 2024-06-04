package br.com.events.event.event.adapter.port;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;

@Api(tags = "Quote Controller")
public interface QuoteControllerV1Port {

    @ApiOperation(value = "Accept and hire a quote")
    ResponseEntity<Void> accept(String quoteRequestUuid);

    @ApiOperation(value = "Decline a quote")
    ResponseEntity<Void> decline(String quoteRequestUuid);

    @ApiOperation(value = "Generate quote contract")
    ResponseEntity<InputStreamResource> generateContract(String quoteRequestUuid);
}
