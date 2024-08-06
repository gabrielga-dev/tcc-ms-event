package br.com.events.event.event.business.command.address;

import br.com.events.event.event.adapter.feing.LocationFeign;
import br.com.events.event.event.data.io.address.city.CityResponseMock;
import br.com.events.event.event.data.model.AddressMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link BuildAddressResponseCommand}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class BuildAddressResponseCommandTest {

    @InjectMocks
    private BuildAddressResponseCommand command;

    @Mock
    private LocationFeign locationFeign;

    @Test
    @DisplayName("execute - when called return address response")
    void executeWhenCalledReturnAddressResponse() {
        var feignMockedResponse = CityResponseMock.build();
        when(locationFeign.getCityByIdAndStateAndCountryIso(anyString(), anyString(), anyLong()))
                .thenReturn(feignMockedResponse);
        var address = AddressMock.build();
        var returned = command.execute(address);

        Assertions.assertNotNull(returned);
        Assertions.assertEquals(address.getStreet(), returned.getStreet());
        Assertions.assertEquals(feignMockedResponse.getId(), address.getCity());

        verify(locationFeign, times(1)).getCityByIdAndStateAndCountryIso(anyString(), anyString(), anyLong());
    }

}
