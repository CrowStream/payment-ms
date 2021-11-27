package crowstream.microservice.payment.adapters.persistence.entities;

import javax.persistence.*;
import java.time.OffsetDateTime;

@Entity
@Table(name = "payment")
public class PaymentEntity {
    @Id
    @SequenceGenerator(name = "payment_id_generator",
            sequenceName = "public.payment_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "payment_id_generator")
    private Long id;

    @Column(name = "account_id", nullable = false)
    private String accountId;

    @OneToOne
    @JoinColumn(name = "method_id", referencedColumnName = "id")
    private PaymentMethodEntity paymentMethod;

    @Column(name = "amount_applied", nullable = false)
    private Double amountApplied;

    @Column(name = "payment_date", columnDefinition = "TIMESTAMPTZ", nullable = false)
    private OffsetDateTime paymentDate;

    @Column(name = "description")
    private String description;

    @Column(name = "status", nullable = false)
    private Integer status;

    @OneToOne(mappedBy = "payment")
    private InvoiceEntity invoice;

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

    public PaymentMethodEntity getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethodEntity paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Double getAmountApplied() {
        return amountApplied;
    }

    public void setAmountApplied(Double amountApplied) {
        this.amountApplied = amountApplied;
    }

    public OffsetDateTime getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(OffsetDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public InvoiceEntity getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceEntity invoice) {
        this.invoice = invoice;
    }
}
