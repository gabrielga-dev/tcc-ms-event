package br.com.events.event.event.application.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.events.event.event.domain.model.Address;
import br.com.events.event.event.domain.model.Contract;
import br.com.events.event.event.domain.model.Event;
import br.com.events.event.event.domain.model.EventService;
import br.com.events.event.event.domain.model.pk.ContractPk;
import br.com.events.event.event.domain.model.pk.EventServicePk;
import br.com.events.event.event.domain.repository.EventRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class Test {

    private final EventRepository eventRepository;
    @GetMapping
    public ResponseEntity<Void> t(){
        var event = new Event();

        var address = new Address();
        address.setEventUuid(event.getUuid());
        address.setStreet("street");
        address.setNeighbour("neighbour");
        address.setComplement("complement");
        address.setCity("city");
        address.setState("state");
        address.setCountry("country");
        address.setZipCode("zipCode");
        address.setLatitude(BigDecimal.TEN);
        address.setLongitude(BigDecimal.TEN);

        event.setAddress(address);
        event.setName("name");
        event.setDescription("description");
        event.setDate(LocalDateTime.now());
        event.setCreationDate(LocalDateTime.now());
        event.setOwnerUuid("ownerUuid");

        var service = new EventService();
        service.setPk(new EventServicePk(event.getUuid(), "TEST"));
        service.setPrice(BigDecimal.TEN);
        event.setServices(List.of(service));
        service.setEvent(event);

        var contract = new Contract();
        contract.setPk(new ContractPk(service.getPk(), "TEST"));
        contract.setBytes(new byte[100]);
        contract.setEventService(service);
        contract.setCreationDate(LocalDateTime.now());
        service.setContracts(List.of(contract));

        eventRepository.save(event);

        return ResponseEntity.noContent().build();
    }
}
