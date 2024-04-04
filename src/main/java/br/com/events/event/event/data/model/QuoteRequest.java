package br.com.events.event.event.data.model;

import br.com.events.event.event.data.model.pk.EventServicePk;
import br.com.events.event.event.data.model.type.QuoteRequestStatusType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "quote_request")
public class QuoteRequest {

    @EmbeddedId
    private EventServicePk pk;

    @Column(name = "quote_uuid")
    private String quoteUuid;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private QuoteRequestStatusType status;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("eventUuid")
    private Event event;
}
