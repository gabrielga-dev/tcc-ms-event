package br.com.events.event.event.business.command.business_type.band.impl;


import br.com.events.event.event.MockConstants;
import br.com.events.event.event.adapter.feing.BandMsBandFeign;
import br.com.events.event.event.data.io.outbound.ms_band.band.BandProfileMsBandResponseMock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Tests for {@link FindBandCommandImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class FindBandCommandImplTest {

    @InjectMocks
    private FindBandCommandImpl command;

    @Mock
    private BandMsBandFeign bandMsBandFeign;

    @Test
    @DisplayName("execute - when profile is found, then returns found profile")
    void executeWhenProfileIsFoundThenReturnsFoundProfile() {
        var expectedProfile = BandProfileMsBandResponseMock.build();
        when(bandMsBandFeign.findBandProfile(anyString())).thenReturn(expectedProfile);

        var returned = command.execute(MockConstants.STRING);

        Assertions.assertNotNull(returned);
        Assertions.assertEquals(expectedProfile, returned);

        verify(bandMsBandFeign, times(1)).findBandProfile(anyString());
    }
}
