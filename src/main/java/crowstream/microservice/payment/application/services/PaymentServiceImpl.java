package crowstream.microservice.payment.application.services;

import crowstream.microservice.payment.application.domain.Payment;
import crowstream.microservice.payment.application.ports.PaymentPersistencePort;
import crowstream.microservice.payment.application.ports.PaymentServicePort;

import java.util.List;

public class PaymentServiceImpl implements PaymentServicePort {
    private final PaymentPersistencePort paymentPersistencePort;

    public PaymentServiceImpl(PaymentPersistencePort paymentPersistencePort) {
        this.paymentPersistencePort = paymentPersistencePort;
    }

    @Override
    public Payment addPayment(Payment payment) {
        return this.paymentPersistencePort.save(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return this.paymentPersistencePort.findById(id);
    }

    @Override
    public List<Payment> getPaymentsByAccountId(String accountId) {
        return this.paymentPersistencePort.findTop12ByAccountId(accountId);
    }
}
