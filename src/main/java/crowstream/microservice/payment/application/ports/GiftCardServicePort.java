package crowstream.microservice.payment.application.ports;

import crowstream.microservice.payment.application.domain.GiftCard;

public interface GiftCardServicePort {
    GiftCard addGiftCard(GiftCard card);
    GiftCard getGiftCardById(Long id);
    GiftCard getGiftCardByCardCode(String cardCode);
    GiftCard updateGiftCard(GiftCard card);
    void deleteGiftCardById(Long id);
}
