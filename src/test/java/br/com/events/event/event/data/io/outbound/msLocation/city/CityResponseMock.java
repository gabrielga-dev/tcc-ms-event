package br.com.events.event.event.data.io.outbound.msLocation.city;


import br.com.events.event.event.MockConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CityResponseMock {

    public static CityResponse build() {
        return CityResponse
                .builder()
                .id(MockConstants.LONG)
                .name(MockConstants.STRING)
                .build();
    }
}
