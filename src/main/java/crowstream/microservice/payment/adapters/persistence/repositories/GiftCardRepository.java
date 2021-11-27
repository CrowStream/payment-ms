package crowstream.microservice.payment.adapters.persistence.repositories;

import crowstream.microservice.payment.adapters.persistence.entities.GiftCardEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GiftCardRepository extends CrudRepository<GiftCardEntity, Long> {

}
