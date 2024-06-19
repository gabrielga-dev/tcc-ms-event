package br.com.events.event.event.adapter.port;

import br.com.events.event.event.data.io.inbound.dashboard.response.DashboardResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
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

    @ApiOperation(value = "Generate the contractor dashboard")
    @ApiImplicitParam(
            name = "Authorization",
            value = "Authorization token",
            required = true,
            paramType = "header",
            dataTypeClass = String.class
    )
    ResponseEntity<DashboardResponse> dashboard();
}
