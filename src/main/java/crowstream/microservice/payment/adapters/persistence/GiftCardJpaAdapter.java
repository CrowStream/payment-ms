package crowstream.microservice.payment.adapters.persistence;

import crowstream.microservice.payment.adapters.persistence.entities.GiftCardEntity;
import crowstream.microservice.payment.adapters.persistence.mappers.GiftCardMapper;
import crowstream.microservice.payment.adapters.persistence.repositories.GiftCardRepository;
import crowstream.microservice.payment.application.domain.GiftCard;
import crowstream.microservice.payment.application.ports.GiftCardPersistencePort;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GiftCardJpaAdapter implements GiftCardPersistencePort {
    private final GiftCardRepository giftCardRepository;
    private final GiftCardMapper giftCardMapper;

    public GiftCardJpaAdapter(GiftCardRepository giftCardRepository) {
        this.giftCardRepository = giftCardRepository;
        this.giftCardMapper = new GiftCardMapper();
    }

    @Override
    public GiftCard save(GiftCard card) {
        GiftCardEntity entity = this.giftCardMapper.pojoToEntity(card);
        GiftCardEntity saved = this.giftCardRepository.save(entity);
        return this.giftCardMapper.entityToPojo(saved);
    }

    @Override
    public GiftCard findById(Long id) {
        Optional<GiftCardEntity> entity = this.giftCardRepository.findById(id);
        return entity.map(this.giftCardMapper::entityToPojo).orElse(null);
    }

    @Override
    public GiftCard findByCardCode(String cardCode) {
        Optional<GiftCardEntity> entity = this.giftCardRepository.findByCardCode(cardCode);
        return entity.map(this.giftCardMapper::entityToPojo).orElse(null);
    }

    @Override
    public Boolean existsByCardCode(String cardCode) {
        return this.giftCardRepository.countByCardCode(cardCode) > 0;
    }

    @Override
    public GiftCard update(GiftCard card) {
        Optional<GiftCardEntity> optionalGiftCard = this.giftCardRepository.findById(card.getId());
        if (optionalGiftCard.isEmpty()) {
            return null;
        }
        GiftCardEntity entity = optionalGiftCard.get();
        entity.setCardCode(card.getCardCode());
        entity.setAmount(card.getAmount());
        entity.setActive(card.getActive());
        entity.setExpirationDate(card.getExpirationDate());
        return this.giftCardMapper.entityToPojo(this.giftCardRepository.save(entity));
    }

    @Override
    public void deleteById(Long id) {
        if (!this.giftCardRepository.existsById(id)) {
            return;
        }
        this.giftCardRepository.deleteById(id);
    }
}
