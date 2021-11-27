package crowstream.microservice.payment.application.ports;

import crowstream.microservice.payment.application.domain.PaymentMethod;

import java.util.List;
import java.util.Optional;

public interface PaymentMethodPersistencePort {
    PaymentMethod save(PaymentMethod paymentMethod);
    Optional<PaymentMethod> findById(Long id);
    List<PaymentMethod> findAllByAccountId(String accountId);
    PaymentMethod update(PaymentMethod paymentMethod);
    void deleteById(Long id);
    void deleteAllByAccountId(String accountId);
}
