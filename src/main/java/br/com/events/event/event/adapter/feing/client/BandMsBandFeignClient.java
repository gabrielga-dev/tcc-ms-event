package br.com.events.event.event.adapter.feing.client;

import br.com.events.event.event.adapter.feing.BandMsBandFeign;
import br.com.events.event.event.adapter.feing.client.config.MyEventFeignClientConfiguration;
import br.com.events.event.event.data.io.outbound.ms_band.band.BandProfileMsBandResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "band-ms-band",
        url = "${feign.client.ms.band.url}",
        configuration = MyEventFeignClientConfiguration.class
)
public interface BandMsBandFeignClient extends BandMsBandFeign {

    @GetMapping("/v1/band/{bandUuid}/profile")
    BandProfileMsBandResponse findBandProfile(@PathVariable("bandUuid") String bandUuid);
}
