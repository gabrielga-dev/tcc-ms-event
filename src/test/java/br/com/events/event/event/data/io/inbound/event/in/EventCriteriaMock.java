package br.com.events.event.event.data.io.inbound.event.in;

import br.com.events.event.event.MockConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EventCriteriaMock {

    public static EventCriteria build(){
        var toReturn = new EventCriteria();

        toReturn.setName(MockConstants.STRING);
        toReturn.setStartDate(MockConstants.LONG);
        toReturn.setFinalDate(MockConstants.LONG);
        toReturn.setConcluded(MockConstants.BOOLEAN);
        toReturn.setOwnerUuid(MockConstants.STRING);

        return toReturn;
    }
}
