package crowstream.microservice.payment.application.ports;

import crowstream.microservice.payment.application.domain.GiftCard;

public interface GiftCardPersistencePort {
    GiftCard save(GiftCard card);
    GiftCard findById(Long id);
    GiftCard findByCardCode(String cardCode);
    Boolean existsByCardCode(String cardCode);
    GiftCard update(GiftCard card);
    void deleteById(Long id);
}
