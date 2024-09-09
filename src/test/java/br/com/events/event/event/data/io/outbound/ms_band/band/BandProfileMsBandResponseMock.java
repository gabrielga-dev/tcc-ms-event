package br.com.events.event.event.data.io.outbound.ms_band.band;


import br.com.events.event.event.MockConstants;
import br.com.events.event.event.data.io.inbound.address.out.AddressResponseMock;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class BandProfileMsBandResponseMock {

    public static BandProfileMsBandResponse build() {
        var toReturn = new BandProfileMsBandResponse();

        toReturn.setUuid(MockConstants.STRING);
        toReturn.setName(MockConstants.STRING);
        toReturn.setDescription(MockConstants.STRING);
        toReturn.setActive(MockConstants.BOOLEAN);
        toReturn.setCreationDateMilliseconds(MockConstants.LONG);
        toReturn.setProfilePictureUuid(MockConstants.STRING);
        toReturn.setOwnerUuid(MockConstants.STRING);
        toReturn.setAddress(AddressResponseMock.build());

        return toReturn;
    }
}
