package crowstream.microservice.payment.adapters.persistence.repositories;

import crowstream.microservice.payment.adapters.persistence.entities.InvoiceEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface InvoiceRepository extends CrudRepository<InvoiceEntity, Long> {
    List<InvoiceEntity> findAllByAccountId(String accountId);

    @Transactional
    void deleteAllByAccountId(String accountId);
}
