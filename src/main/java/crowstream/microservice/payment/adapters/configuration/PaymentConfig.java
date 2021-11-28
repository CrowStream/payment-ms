package crowstream.microservice.payment.adapters.configuration;

import crowstream.microservice.payment.adapters.persistence.PaymentJpaAdapter;
import crowstream.microservice.payment.adapters.persistence.repositories.PaymentMethodRepository;
import crowstream.microservice.payment.adapters.persistence.repositories.PaymentRepository;
import crowstream.microservice.payment.application.ports.PaymentPersistencePort;
import crowstream.microservice.payment.application.ports.PaymentServicePort;
import crowstream.microservice.payment.application.services.PaymentServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentConfig {
    @Bean
    public PaymentPersistencePort paymentPersistence(PaymentRepository repository, PaymentMethodRepository paymentMethodRepository) {
        return new PaymentJpaAdapter(repository, paymentMethodRepository);
    }

    @Bean
    public PaymentServicePort paymentService(PaymentRepository repository, PaymentMethodRepository paymentMethodRepository) {
        return new PaymentServiceImpl(paymentPersistence(repository, paymentMethodRepository));
    }
}
