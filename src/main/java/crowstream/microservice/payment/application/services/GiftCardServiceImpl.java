package crowstream.microservice.payment.application.services;

import crowstream.microservice.payment.application.domain.GiftCard;
import crowstream.microservice.payment.application.ports.GiftCardPersistencePort;
import crowstream.microservice.payment.application.ports.GiftCardServicePort;

public class GiftCardServiceImpl implements GiftCardServicePort {
    private final GiftCardPersistencePort persistencePort;

    public GiftCardServiceImpl(GiftCardPersistencePort persistencePort) {
        this.persistencePort = persistencePort;
    }

    @Override
    public GiftCard addGiftCard(GiftCard card) {
        if (this.persistencePort.existsByCardCode(card.getCardCode())) {
            return null;
        }
        return this.persistencePort.save(card);
    }

    @Override
    public GiftCard getGiftCardById(Long id) {
        return this.persistencePort.findById(id);
    }

    @Override
    public GiftCard getGiftCardByCardCode(String cardCode) {
        return this.persistencePort.findByCardCode(cardCode);
    }

    @Override
    public GiftCard updateGiftCard(GiftCard card) {
        if (!this.persistencePort.existsByCardCode(card.getCardCode())) {
            return null;
        }
        return this.persistencePort.update(card);
    }

    @Override
    public void deleteGiftCardById(Long id) {
        if (this.persistencePort.findById(id) == null) {
            return;
        }
        this.persistencePort.deleteById(id);
    }
}
