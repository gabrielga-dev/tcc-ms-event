package br.com.events.event.event.business.command.business_type.band;

import br.com.events.event.event.data.io.outbound.ms_band.band.BandProfileMsBandResponse;

public interface FindBandCommand {

    BandProfileMsBandResponse execute(String bandUuid);
}
