package br.com.events.event.event.data.model;


import br.com.events.event.event.MockConstants;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class EventMock {
    public static Event build() {
        return build(new ArrayList<>(List.of(QuoteRequestMock.build())));
    }

    public static Event build(List<QuoteRequest> quoteRequests) {
        var toReturn = new Event();

        toReturn.setName(MockConstants.STRING);
        toReturn.setDescription(MockConstants.STRING);
        toReturn.setDate(MockConstants.LOCAL_DATE_TIME);
        toReturn.setCreationDate(MockConstants.LOCAL_DATE_TIME);
        toReturn.setUpdateDate(MockConstants.LOCAL_DATE_TIME);
        toReturn.setOwnerUuid(MockConstants.STRING);
        toReturn.setAddress(AddressMock.build());
        toReturn.setQuotes(quoteRequests);

        return toReturn;
    }
}
