package crowstream.microservice.payment.adapters.persistence.mappers;

import crowstream.microservice.payment.adapters.persistence.entities.GiftCardEntity;
import crowstream.microservice.payment.application.domain.GiftCard;

public class GiftCardMapper {
    public GiftCardEntity pojoToEntity(GiftCard pojo) {
        GiftCardEntity entity = new GiftCardEntity();

        return entity;
    }

    public GiftCard entityToPojo(GiftCardEntity entity) {
        GiftCard pojo = new GiftCard();

        return pojo;
    }
}
