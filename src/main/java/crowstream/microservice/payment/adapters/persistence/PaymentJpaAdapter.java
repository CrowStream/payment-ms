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
        Optional<PaymentMethodEntity> optionalMethod = this.methodRepository.findById(payment.getMethodId());
        PaymentEntity entity = this.mapper.pojoToEntity(payment, optionalMethod.orElse(null));
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
}
