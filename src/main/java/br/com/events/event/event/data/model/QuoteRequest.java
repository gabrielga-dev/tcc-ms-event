package br.com.events.event.event.data.model;

import br.com.events.event.event.data.io.outbound.ms_band.message.quote.QuoteAnsweredMessage;
import br.com.events.event.event.data.model.type.BusinessType;
import br.com.events.event.event.data.model.type.QuoteRequestStatusType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

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

    @Id
    private String uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    private Event event;

    @Column(name = "business_uuid", nullable = false)
    private String businessUuid;

    @Column(name = "business_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private BusinessType businessType;

    @Column(name = "quote_uuid")
    private String quoteUuid;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private QuoteRequestStatusType status;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "update_date")
    private LocalDateTime updateDate;

    @Column(name = "price")
    private BigDecimal price;

    @Column(name = "business_observation")
    private String businessObservation;

    public QuoteRequest(Event event, BusinessType businessType, String businessUuid) {
        this.uuid = UUID.randomUUID().toString();
        this.event = event;
        this.businessUuid = businessUuid;
        this.businessType = businessType;
        this.status = QuoteRequestStatusType.NON_ANSWERED;
        this.creationDate = LocalDateTime.now();
    }

    public void decline() {
        this.status = QuoteRequestStatusType.DECLINED;
        this.updateDate = LocalDateTime.now();
    }

    public void update(QuoteAnsweredMessage quoteAnsweredMessage) {
        this.price = quoteAnsweredMessage.getPrice();
        this.businessObservation = quoteAnsweredMessage.getObservation();
        this.quoteUuid = quoteAnsweredMessage.getMsBandQuoteUuid();
        this.status = QuoteRequestStatusType.ANSWERED;
        this.updateDate = LocalDateTime.now();
    }

    public void decline(boolean hired) {
        if (hired){
            this.status = QuoteRequestStatusType.HIRED;
        } else {
            this.status = QuoteRequestStatusType.DECLINED;
        }
        this.updateDate = LocalDateTime.now();
    }
}
