package crowstream.microservice.payment.application.ports;

import crowstream.microservice.payment.application.domain.Payment;

import java.util.List;

public interface PaymentPersistencePort {
    Payment save(Payment payment);
    Payment findById(Long id);
    List<Payment> findTop12ByAccountId(String accountId);
    Boolean existsById(Long id);
}
