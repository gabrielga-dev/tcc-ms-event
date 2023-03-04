package br.com.events.event.event.domain.model;

import br.com.events.event.event.domain.model.pk.ContractPk;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * This class represents the event contract's database table
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "contract")
public class Contract {

    @EmbeddedId
    private ContractPk pk;

    @Lob
    @Column(name = "bytes", columnDefinition = "MEDIUMBLOB", nullable = false)
    private byte[] bytes;

    @Column(name = "creation_date", nullable = false)
    private LocalDateTime creationDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @MapsId("eventServicePk")
    @JoinColumns({
        @JoinColumn(name="event_uuid", referencedColumnName="event_uuid"),
        @JoinColumn(name="business_uuid", referencedColumnName="business_uuid")
    })
    @ManyToOne(fetch = FetchType.LAZY)
    private EventService eventService;
}
