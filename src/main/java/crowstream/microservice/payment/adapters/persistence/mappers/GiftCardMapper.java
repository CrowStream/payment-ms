package crowstream.microservice.payment.adapters.persistence.mappers;

import crowstream.microservice.payment.adapters.persistence.entities.GiftCardEntity;
import crowstream.microservice.payment.application.domain.GiftCard;

public class GiftCardMapper {
    public GiftCardEntity pojoToEntity(GiftCard pojo) {
        GiftCardEntity entity = new GiftCardEntity();
        entity.setCardCode(pojo.getCardCode());
        entity.setAmount(pojo.getAmount());
        entity.setActive(pojo.getActive());
        entity.setExpirationDate(pojo.getExpirationDate());
        return entity;
    }

    public GiftCard entityToPojo(GiftCardEntity entity) {
        GiftCard pojo = new GiftCard();
        pojo.setId(entity.getId());
        pojo.setCardCode(entity.getCardCode());
        pojo.setAmount(entity.getAmount());
        pojo.setActive(entity.getActive());
        pojo.setExpirationDate(entity.getExpirationDate());
        return pojo;
    }
}
