package crowstream.microservice.payment.adapters.persistence;

import crowstream.microservice.payment.adapters.persistence.entities.PaymentEntity;
import crowstream.microservice.payment.adapters.persistence.entities.PaymentMethodEntity;
import crowstream.microservice.payment.adapters.persistence.mappers.PaymentMapper;
import crowstream.microservice.payment.adapters.persistence.repositories.PaymentMethodRepository;
import crowstream.microservice.payment.adapters.persistence.repositories.PaymentRepository;
import crowstream.microservice.payment.application.domain.Payment;
import crowstream.microservice.payment.application.ports.PaymentPersistencePort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentJpaAdapter implements PaymentPersistencePort {
    private final PaymentRepository repository;
    private final PaymentMethodRepository methodRepository;
    private final PaymentMapper mapper;

    public PaymentJpaAdapter(PaymentRepository repository, PaymentMethodRepository methodRepository) {
        this.repository = repository;
        this.methodRepository = methodRepository;
        this.mapper = new PaymentMapper();
    }

    @Override
    public Payment save(Payment payment) {
        PaymentEntity entity;
        if (payment.getMethodId() != null) {
            Optional<PaymentMethodEntity> optionalMethod = this.methodRepository.findById(payment.getMethodId());
            if (optionalMethod.isEmpty()) {
                return null;
            }
            if (!payment.getAccountId().equals(optionalMethod.get().getAccountId())) {
                return null;
            }
            entity = this.mapper.pojoToEntity(payment, optionalMethod.orElse(null));
        } else {
            entity = this.mapper.pojoToEntity(payment, null);
        }
        PaymentEntity saved = this.repository.save(entity);
        return this.mapper.entityToPojo(saved);
    }

    @Override
    public Payment findById(Long id) {
        Optional<PaymentEntity> entity = this.repository.findById(id);
        return entity.map(this.mapper::entityToPojo).orElse(null);
    }

    @Override
    public List<Payment> findTop12ByAccountId(String accountId) {
        List<PaymentEntity> entities = this.repository.findTop12ByAccountIdOrderByPaymentDateDesc(accountId);
        List<Payment> paymentList = new ArrayList<>();
        for (PaymentEntity entity : entities) {
            paymentList.add(this.mapper.entityToPojo(entity));
        }
        return paymentList;
    }

    @Override
    public Boolean existsById(Long id) {
        return this.repository.countById(id) > 0;
    }

    @Override
    public Payment update(Payment payment) {
        Optional<PaymentEntity> optionalPayment = this.repository.findById(payment.getId());
        if (optionalPayment.isEmpty()) {
            return null;
        }
        PaymentEntity entity = optionalPayment.get();
        entity.setAmountApplied(payment.getAmountApplied());
        entity.setPaymentDate(payment.getPaymentDate());
        entity.setDescription(payment.getDescription());
        entity.setStatus(payment.getStatus());
        if (payment.getMethodId() != null) {
            Optional<PaymentMethodEntity> optionalMethod = this.methodRepository.findById(payment.getMethodId());
            entity.setPaymentMethod(optionalMethod.orElse(null));
        }
        return this.mapper.entityToPojo(this.repository.save(entity));
    }

    @Override
    public void deleteById(Long id) {
        if (!this.repository.existsById(id)) {
            return;
        }
        this.repository.deleteById(id);
    }
}
