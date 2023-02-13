package br.com.events.event.event.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.events.event.event.domain.model.pk.EventServicePk;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class represents the event service's database table
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "event_service")
public class EventService {

    @EmbeddedId
    private EventServicePk pk;

    @Column(name = "hired", nullable = false)
    private Boolean hired = Boolean.FALSE;

    @Column(name = "hired_date")
    private LocalDateTime hiredDate;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("eventUuid")
    private Event event;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "eventService", cascade = CascadeType.ALL)
    private List<Contract> contracts;

}
