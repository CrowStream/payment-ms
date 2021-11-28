package crowstream.microservice.payment.adapters.persistence;

import crowstream.microservice.payment.adapters.persistence.entities.GiftCardEntity;
import crowstream.microservice.payment.adapters.persistence.entities.PaymentMethodEntity;
import crowstream.microservice.payment.adapters.persistence.mappers.PaymentMethodMapper;
import crowstream.microservice.payment.adapters.persistence.repositories.GiftCardRepository;
import crowstream.microservice.payment.adapters.persistence.repositories.PaymentMethodRepository;
import crowstream.microservice.payment.application.domain.PaymentMethod;
import crowstream.microservice.payment.application.ports.PaymentMethodPersistencePort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentMethodJpaAdapter implements PaymentMethodPersistencePort {
    private final PaymentMethodRepository repository;
    private final GiftCardRepository giftCardRepository;
    private final PaymentMethodMapper mapper;

    public PaymentMethodJpaAdapter(PaymentMethodRepository repository, GiftCardRepository giftCardRepository) {
        this.repository = repository;
        this.giftCardRepository = giftCardRepository;
        this.mapper = new PaymentMethodMapper();
    }

    @Override
    public PaymentMethod save(PaymentMethod paymentMethod) {
        PaymentMethodEntity entity;
        if (paymentMethod.getGiftCardId() != null) {
            Optional<GiftCardEntity> optionalCard = this.giftCardRepository.findById(paymentMethod.getGiftCardId());
            entity = this.mapper.pojoToEntity(paymentMethod, optionalCard.orElse(null));
        } else {
            entity = this.mapper.pojoToEntity(paymentMethod, null);
        }
        PaymentMethodEntity saved = this.repository.save(entity);
        return this.mapper.entityToPojo(saved);
    }

    @Override
    public PaymentMethod findById(Long id) {
        Optional<PaymentMethodEntity> entity = this.repository.findById(id);
        return entity.map(this.mapper::entityToPojo).orElse(null);
    }

    @Override
    public List<PaymentMethod> findAllByAccountId(String accountId) {
        List<PaymentMethodEntity> entities = this.repository.findAllByAccountId(accountId);
        List<PaymentMethod> methodsList = new ArrayList<>();
        for (PaymentMethodEntity entity : entities) {
            methodsList.add(this.mapper.entityToPojo(entity));
        }
        return methodsList;
    }

    @Override
    public Boolean existsById(Long id) {
        return this.repository.countById(id) > 0;
    }

    @Override
    public PaymentMethod update(PaymentMethod paymentMethod) {
        if (!this.repository.existsById(paymentMethod.getId())) {
            return null;
        }
        return this.save(paymentMethod);
    }

    @Override
    public void deleteById(Long id) {
        if (!this.repository.existsById(id)) {
            return;
        }
        this.repository.deleteById(id);
    }

    @Override
    public void deleteAllByAccountId(String accountId) {
        if (this.repository.countByAccountId(accountId) < 1) {
            return;
        }
        this.repository.deleteAllByAccountId(accountId);
    }
}
