package crowstream.microservice.payment.adapters.persistence.entities;

import javax.persistence.*;

@Entity
@Table(name = "payment_method")
public class PaymentMethodEntity {
    @Id
    @SequenceGenerator(name = "payment_method_id_generator",
            sequenceName = "public.payment_method_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "payment_method_id_generator")
    private Long id;

    @Column(name = "account_id", nullable = false)
    private String accountId;

    @OneToOne
    @JoinColumn(name = "gift_card_id", referencedColumnName = "id")
    private GiftCardEntity giftCard;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "card_expiry_date")
    private String cardExpiryDate;

    @Column(name = "card_security_number")
    private String cardSecurityNumber;

    @OneToOne(mappedBy = "paymentMethod")
    private PaymentEntity payment;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public GiftCardEntity getGiftCard() {
        return giftCard;
    }

    public void setGiftCard(GiftCardEntity giftCard) {
        this.giftCard = giftCard;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardExpiryDate() {
        return cardExpiryDate;
    }

    public void setCardExpiryDate(String cardExpiryDate) {
        this.cardExpiryDate = cardExpiryDate;
    }

    public String getCardSecurityNumber() {
        return cardSecurityNumber;
    }

    public void setCardSecurityNumber(String cardSecurityNumber) {
        this.cardSecurityNumber = cardSecurityNumber;
    }

    public PaymentEntity getPayment() {
        return payment;
    }

    public void setPayment(PaymentEntity payment) {
        this.payment = payment;
    }
}
