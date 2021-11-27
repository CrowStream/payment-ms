package crowstream.microservice.payment.application.services;

import crowstream.microservice.payment.application.domain.PaymentMethod;
import crowstream.microservice.payment.application.ports.PaymentMethodPersistencePort;
import crowstream.microservice.payment.application.ports.PaymentMethodServicePort;

import java.util.List;

public class PaymentMethodServiceImpl implements PaymentMethodServicePort {
    private PaymentMethodPersistencePort persistencePort;

    public PaymentMethodServiceImpl(PaymentMethodPersistencePort persistencePort) {
        this.persistencePort = persistencePort;
    }

    @Override
    public PaymentMethod addPaymentMethod(PaymentMethod paymentMethod) {
        return this.persistencePort.save(paymentMethod);
    }

    @Override
    public PaymentMethod getPaymentMethodById(Long id) {
        return this.persistencePort.findById(id);
    }

    @Override
    public List<PaymentMethod> getPaymentMethodsByAccountId(String accountId) {
        return this.persistencePort.findAllByAccountId(accountId);
    }

    @Override
    public PaymentMethod updatePaymentMethod(PaymentMethod paymentMethod) {
        return this.persistencePort.update(paymentMethod);
    }

    @Override
    public void deletePaymentMethodById(Long id) {
        this.persistencePort.deleteById(id);
    }

    @Override
    public void deletePaymentMethodsByAccountId(String accountId) {
        this.persistencePort.deleteAllByAccountId(accountId);
    }
}
