package crowstream.microservice.payment.application.ports;

import crowstream.microservice.payment.application.domain.GiftCard;

import java.util.Optional;

public interface GiftCardPersistencePort {
    GiftCard save(GiftCard card);
    Optional<GiftCard> findById(Long id);
    GiftCard update(GiftCard card);
    void deleteById(Long id);
}
