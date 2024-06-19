package br.com.events.event.event.data.io.outbound.ms_band.band;

import br.com.events.event.event.data.io.inbound.address.out.AddressResponse;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BandProfileMsBandResponse {

    private String uuid;
    private String name;
    private String description;
    private Boolean active;
    private Long creationDateMilliseconds;
    private String profilePictureUuid;
    private String ownerUuid;
    private AddressResponse address;
}
