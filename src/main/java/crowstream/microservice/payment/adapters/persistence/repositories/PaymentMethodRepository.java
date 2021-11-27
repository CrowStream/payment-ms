package crowstream.microservice.payment.adapters.persistence.repositories;

import crowstream.microservice.payment.adapters.persistence.entities.PaymentMethodEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface PaymentMethodRepository extends CrudRepository<PaymentMethodEntity, Long> {
    List<PaymentMethodEntity> findAllByAccountId(String accountId);

    @Transactional
    void deleteAllByAccountId(String accountId);
}
