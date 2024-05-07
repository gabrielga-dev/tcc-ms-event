package br.com.events.event.event.business.use_case.event;

import java.util.List;
import java.util.Map;

public interface FindEventNamesUseCase {

    Map<String, String> execute(List<String> uuids);
}
