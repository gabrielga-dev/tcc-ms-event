package br.com.events.event.event.clean.data.io.inbound.event;

import br.com.events.event.event.clean.data.io.inbound.address.IAddress;

import java.time.LocalDateTime;

public interface IEvent {

    LocalDateTime getDate();
    IAddress getAddress();
}
