package br.com.events.event.event.data.model;


import br.com.events.event.event.MockConstants;
import br.com.events.event.event.data.model.type.BusinessType;
import br.com.events.event.event.data.model.type.QuoteRequestStatusType;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class QuoteRequestMock {

    public static QuoteRequest build() {
        var toReturn = new QuoteRequest();

        toReturn.setUuid(MockConstants.STRING);
        toReturn.setBusinessUuid(MockConstants.STRING);
        toReturn.setBusinessType(BusinessType.BAND);
        toReturn.setQuoteUuid(MockConstants.STRING);
        toReturn.setStatus(QuoteRequestStatusType.NON_ANSWERED);
        toReturn.setCreationDate(MockConstants.LOCAL_DATE_TIME);
        toReturn.setUpdateDate(MockConstants.LOCAL_DATE_TIME);
        toReturn.setPrice(MockConstants.BIG_DECIMAL);
        toReturn.setBusinessObservation(MockConstants.STRING);
        toReturn.setEvent(EventMock.build(new ArrayList<>()));

        return toReturn;
    }
}
