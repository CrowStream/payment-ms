package crowstream.microservice.payment.application.ports;

import crowstream.microservice.payment.application.domain.PaymentMethod;

import java.util.List;

public interface PaymentMethodServicePort {
    PaymentMethod addPaymentMethod(PaymentMethod paymentMethod);
    PaymentMethod getPaymentMethodById(Long id);
    List<PaymentMethod> getPaymentMethodsByAccountId(String accountId);
    PaymentMethod updatePaymentMethod(PaymentMethod paymentMethod);
    void deletePaymentMethodById(Long id);
    void deletePaymentMethodsByAccountId(String accountId);
}
