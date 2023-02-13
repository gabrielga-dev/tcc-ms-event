package br.com.events.event.event.domain.model.pk;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * This class represents the event contract's primary key
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
public class ContractPk implements Serializable {

    private EventServicePk eventServicePk;

    @Column(name = "quote_uuid", nullable = false)
    private String quoteUuid;
}
