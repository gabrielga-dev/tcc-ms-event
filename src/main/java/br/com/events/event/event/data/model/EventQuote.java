package br.com.events.event.event.data.model;

import br.com.events.event.event.data.model.pk.EventServicePk;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * This class represents the event service's database table
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "event_quote")
public class EventQuote {

    @EmbeddedId
    private EventServicePk pk;

    @Column(name = "hired", nullable = false)
    private Boolean hired = Boolean.FALSE;

    @Column(name = "hired_date")
    private LocalDateTime hiredDate;

    @Column(name = "quote_uuid")
    private String quoteUuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("eventUuid")
    private Event event;
}
