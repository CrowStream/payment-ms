package crowstream.microservice.payment.application.ports;

import crowstream.microservice.payment.application.domain.Payment;

import java.util.List;
import java.util.Optional;

public interface PaymentPersistencePort {
    Payment save(Payment payment);
    Optional<Payment> findById(Long id);
    List<Payment> findAllByAccountId(String accountId);
}
