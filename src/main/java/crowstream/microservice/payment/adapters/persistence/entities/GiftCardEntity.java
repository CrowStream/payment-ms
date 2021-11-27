package crowstream.microservice.payment.adapters.persistence.entities;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "gift_card")
public class GiftCardEntity {
    @Id
    @SequenceGenerator(name = "gift_card_id_generator",
            sequenceName = "public.gift_card_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "gift_card_id_generator")
    private Long id;

    @Column(name = "card_code", unique = true, nullable = false)
    private String cardCode;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "is_active", nullable = false)
    private Boolean isActive;

    @Column(name = "expiration_date", columnDefinition = "TIMESTAMPTZ", nullable = false)
    private OffsetDateTime expirationDate;

    @OneToOne(mappedBy = "giftCard")
    private PaymentMethodEntity paymentMethod;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardCode() {
        return cardCode;
    }

    public void setCardCode(String cardCode) {
        this.cardCode = cardCode;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    public OffsetDateTime getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(OffsetDateTime expirationDate) {
        this.expirationDate = expirationDate;
    }

    public PaymentMethodEntity getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodEntity paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
}
