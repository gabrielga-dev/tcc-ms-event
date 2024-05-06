package br.com.events.event.event.data.model.pk;

import br.com.events.event.event.data.model.type.BusinessType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.io.Serializable;

/**
 * This class represents the event service's primary key
 *
 * @author Gabriel Guimarães de Almeida
 */
@Getter
@Setter
@Builder
@Embeddable
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class EventServicePk implements Serializable {

    @Column(name = "event_uuid", nullable = false)
    private String eventUuid;

    @Column(name = "business_uuid", nullable = false)
    private String businessUuid;

    @Column(name = "quote_request_uuid", nullable = false)
    private String quoteRequestUuid;

    @Column(name = "business_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private BusinessType businessType;
}
