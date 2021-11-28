package crowstream.microservice.payment.adapters.persistence.mappers;

import crowstream.microservice.payment.adapters.persistence.entities.PaymentEntity;
import crowstream.microservice.payment.adapters.persistence.entities.PaymentMethodEntity;
import crowstream.microservice.payment.application.domain.Payment;

public class PaymentMapper {
    public PaymentEntity pojoToEntity(Payment pojo, PaymentMethodEntity optionalPaymentMethod) {
        PaymentEntity entity = new PaymentEntity();
        entity.setAccountId(pojo.getAccountId());
        entity.setPaymentMethod(optionalPaymentMethod);
        entity.setAmountApplied(pojo.getAmountApplied());
        entity.setPaymentDate(pojo.getPaymentDate());
        entity.setDescription(pojo.getDescription());
        entity.setStatus(pojo.getStatus());
        return entity;
    }

    public Payment entityToPojo(PaymentEntity entity) {
        Payment pojo = new Payment();
        pojo.setId(entity.getId());
        pojo.setAccountId(entity.getAccountId());
        pojo.setMethodId(entity.getPaymentMethod().getId());
        pojo.setAmountApplied(entity.getAmountApplied());
        pojo.setPaymentDate(entity.getPaymentDate());
        pojo.setDescription(entity.getDescription());
        pojo.setStatus(entity.getStatus());
        return pojo;
    }
}
