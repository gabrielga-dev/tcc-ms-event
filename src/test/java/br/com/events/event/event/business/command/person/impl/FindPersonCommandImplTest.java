package br.com.events.event.event.business.command.person.impl;

import br.com.events.event.event.MockConstants;
import br.com.events.event.event.adapter.feing.PersonMsAuthFeign;
import br.com.events.event.event.data.io.outbound.msAuth.person.findByUuid.out.PersonResponseMock;
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
 * Tests for {@link FindPersonCommandImpl}
 *
 * @author gabriel
 */
@ExtendWith(MockitoExtension.class)
class FindPersonCommandImplTest {

    @InjectMocks
    private FindPersonCommandImpl command;

    @Mock
    private PersonMsAuthFeign personMsAuthFeign;

    @Test
    @DisplayName("execute - when called saves event")
    void executeWhenCalledSavesEvent() {
        var person = PersonResponseMock.build();
        person.setFirstName("Fulano");
        person.setLastName("De Tal");
        when(personMsAuthFeign.findPersonByUuid(anyString())).thenReturn(person);

        var returned = command.execute(MockConstants.STRING);

        Assertions.assertNotNull(returned);
        Assertions.assertEquals(person, returned);
        Assertions.assertEquals("Fulano De Tal", returned.getCompleteName());

        verify(personMsAuthFeign, times(1)).findPersonByUuid(anyString());
    }
}
