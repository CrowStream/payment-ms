package crowstream.microservice.payment.application.ports;

import crowstream.microservice.payment.application.domain.PaymentMethod;

import java.util.List;

public interface PaymentMethodPersistencePort {
    PaymentMethod save(PaymentMethod paymentMethod);
    PaymentMethod findById(Long id);
    List<PaymentMethod> findAllByAccountId(String accountId);
    PaymentMethod update(PaymentMethod paymentMethod);
    void deleteById(Long id);
    void deleteAllByAccountId(String accountId);
}
