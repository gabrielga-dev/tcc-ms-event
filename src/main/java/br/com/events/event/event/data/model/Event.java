package br.com.events.event.event.data.model;

import br.com.events.event.event.data.io.inbound.auth.AuthenticatedPerson;
import br.com.events.event.event.data.io.inbound.event.in.EventRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * This class represents the event's database table
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "event")
public class Event {

    @Id
    @Column(name = "uuid")
    private String uuid = UUID.randomUUID().toString();

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "date")
    private LocalDateTime date;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Column(name = "active")
    private Boolean active = Boolean.TRUE;

    @Column(name = "owner_uuid", nullable = false)
    private String ownerUuid;

    @OneToOne(fetch = FetchType.EAGER, mappedBy = "event", cascade = CascadeType.ALL)
    private Address address;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "event", cascade = CascadeType.ALL)
    private List<QuoteRequest> quotes;

    public Event(EventRequest form, AuthenticatedPerson authenticated) {
        this.uuid = UUID.randomUUID().toString();
        this.name = form.getName();
        this.description = form.getDescription();
        this.date = form.getDate();
        this.creationDate = LocalDateTime.now();

        this.ownerUuid = authenticated.getUuid();

        this.address = new Address(form.getAddress());
        this.address.setEvent(this);
    }

    public void cancel() {
        this.active = Boolean.FALSE;
        this.updateDate = LocalDateTime.now();
    }

    public boolean alreadyHappened() {
        return LocalDateTime.now().isAfter(this.date);
    }
}
