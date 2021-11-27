package crowstream.microservice.payment.adapters.persistence.repositories;

import crowstream.microservice.payment.adapters.persistence.entities.PaymentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends CrudRepository<PaymentEntity, Long> {
    List<PaymentEntity> findAllByAccountId(String accountId);
}
