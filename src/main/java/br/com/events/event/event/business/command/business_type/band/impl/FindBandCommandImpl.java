package br.com.events.event.event.business.command.business_type.band.impl;

import br.com.events.event.event.adapter.feing.BandMsBandFeign;
import br.com.events.event.event.business.command.business_type.band.FindBandCommand;
import br.com.events.event.event.data.io.outbound.ms_band.band.BandProfileMsBandResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FindBandCommandImpl implements FindBandCommand {

    private final BandMsBandFeign bandMsBandFeign;

    @Override
    public BandProfileMsBandResponse execute(String bandUuid) {
        return bandMsBandFeign.findBandProfile(bandUuid);
    }
}
