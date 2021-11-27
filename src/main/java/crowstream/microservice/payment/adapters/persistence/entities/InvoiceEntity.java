package crowstream.microservice.payment.adapters.persistence.entities;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "invoice")
public class InvoiceEntity {
    @Id
    @SequenceGenerator(name = "invoice_id_generator",
        sequenceName = "public.invoice_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "invoice_id_generator")
    private Long id;

    @Column(name = "account_id", nullable = false)
    private String accountId;

    @Column(name = "amount", nullable = false)
    private Double amount;

    @Column(name = "created_at", columnDefinition = "TIMESTAMPTZ", nullable = false)
    private OffsetDateTime createdAt;

    @Column(name = "limit_date", columnDefinition = "TIMESTAMPTZ", nullable = false)
    private OffsetDateTime limitDate;

    @OneToOne
    @JoinColumn(name = "payment_id", referencedColumnName = "id")
    private PaymentEntity payment;

    @Column(name = "state", nullable = false)
    private Integer state;

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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public OffsetDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(OffsetDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public OffsetDateTime getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(OffsetDateTime limitDate) {
        this.limitDate = limitDate;
    }

    public PaymentEntity getPayment() {
        return payment;
    }

    public void setPayment(PaymentEntity payment) {
        this.payment = payment;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
