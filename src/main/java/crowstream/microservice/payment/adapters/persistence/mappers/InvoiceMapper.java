package crowstream.microservice.payment.adapters.persistence.mappers;

import crowstream.microservice.payment.adapters.persistence.entities.InvoiceEntity;
import crowstream.microservice.payment.adapters.persistence.entities.PaymentEntity;
import crowstream.microservice.payment.application.domain.Invoice;

public class InvoiceMapper {
    public InvoiceEntity pojoToEntity(Invoice pojo, PaymentEntity optionalPayment) {
        InvoiceEntity entity = new InvoiceEntity();
        entity.setAccountId(pojo.getAccountId());
        entity.setAmount(pojo.getAmount());
        entity.setCreatedAt(pojo.getCreatedAt());
        entity.setLimitDate(pojo.getLimitDate());
        entity.setPayment(optionalPayment);
        entity.setState(pojo.getState());
        return entity;
    }

    public Invoice entityToPojo(InvoiceEntity entity) {
        Invoice pojo = new Invoice();
        pojo.setId(entity.getId());
        pojo.setAccountId(pojo.getAccountId());
        pojo.setAmount(entity.getAmount());
        pojo.setCreatedAt(entity.getCreatedAt());
        pojo.setLimitDate(entity.getLimitDate());
        if (entity.getPayment() != null)
            pojo.setPaymentId(entity.getPayment().getId());
        pojo.setState(entity.getState());
        return pojo;
    }
}
