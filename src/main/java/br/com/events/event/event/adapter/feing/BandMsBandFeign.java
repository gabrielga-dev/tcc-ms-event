package br.com.events.event.event.adapter.feing;

import br.com.events.event.event.data.io.outbound.ms_band.band.BandProfileMsBandResponse;

public interface BandMsBandFeign {

    BandProfileMsBandResponse findBandProfile(String bandUuid);
}
