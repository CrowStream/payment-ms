package crowstream.microservice.payment.adapters.persistence.mappers;

import crowstream.microservice.payment.adapters.persistence.entities.GiftCardEntity;
import crowstream.microservice.payment.adapters.persistence.entities.PaymentMethodEntity;
import crowstream.microservice.payment.application.domain.PaymentMethod;

public class PaymentMethodMapper {
    public PaymentMethodEntity pojoToEntity(PaymentMethod pojo, GiftCardEntity optionalCard) {
        PaymentMethodEntity entity = new PaymentMethodEntity();
        entity.setAccountId(pojo.getAccountId());
        entity.setGiftCard(optionalCard);
        entity.setCardNumber(pojo.getCardNumber());
        entity.setCardExpiryDate(pojo.getCardExpiryDate());
        entity.setCardSecurityNumber(pojo.getCardSecurityNumber());
        return entity;
    }

    public PaymentMethod entityToPojo(PaymentMethodEntity entity) {
        PaymentMethod pojo = new PaymentMethod();
        pojo.setId(entity.getId());
        pojo.setAccountId(entity.getAccountId());
        pojo.setGiftCardId(entity.getGiftCard().getId());
        pojo.setCardNumber(entity.getCardNumber());
        pojo.setCardExpiryDate(entity.getCardExpiryDate());
        pojo.setCardSecurityNumber(entity.getCardSecurityNumber());
        return pojo;
    }
}
