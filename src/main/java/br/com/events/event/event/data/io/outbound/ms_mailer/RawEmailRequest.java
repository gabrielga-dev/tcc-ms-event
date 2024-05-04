package br.com.events.event.event.data.io.outbound.ms_mailer;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

@Getter
@ToString
@RequiredArgsConstructor
public class RawEmailRequest implements Serializable {

    private final EmailRequestType type;
    private final Map<String, String> keyAndValues;
}
