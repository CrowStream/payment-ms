package crowstream.microservice.payment.application.ports;

import crowstream.microservice.payment.application.domain.GiftCard;

public interface GiftCardPersistencePort {
    GiftCard save(GiftCard card);
    GiftCard findById(Long id);
    GiftCard update(GiftCard card);
    void deleteById(Long id);
}
