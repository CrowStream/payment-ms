package crowstream.microservice.payment.application.ports;

import crowstream.microservice.payment.application.domain.Payment;

import java.util.List;

public interface PaymentServicePort {
    Payment addPayment(Payment payment);
    Payment getPaymentById(Long id);
    List<Payment> getPaymentsByAccountId(String accountId);
    Payment update(Payment payment);
    void deletePaymentById(Long id);
}
