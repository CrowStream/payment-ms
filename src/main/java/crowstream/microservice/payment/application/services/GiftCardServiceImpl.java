package crowstream.microservice.payment.application.services;

import crowstream.microservice.payment.application.domain.GiftCard;
import crowstream.microservice.payment.application.ports.GiftCardPersistencePort;
import crowstream.microservice.payment.application.ports.GiftCardServicePort;

public class GiftCardServiceImpl implements GiftCardServicePort {
    private GiftCardPersistencePort persistencePort;

    public GiftCardServiceImpl(GiftCardPersistencePort persistencePort) {
        this.persistencePort = persistencePort;
    }

    @Override
    public GiftCard addGiftCard(GiftCard card) {
        return this.persistencePort.save(card);
    }

    @Override
    public GiftCard getGiftCardById(Long id) {
        return this.persistencePort.findById(id);
    }

    @Override
    public GiftCard updateGiftCard(GiftCard card) {
        return this.persistencePort.update(card);
    }

    @Override
    public void deleteGiftCardById(Long id) {
        this.persistencePort.deleteById(id);
    }
}
