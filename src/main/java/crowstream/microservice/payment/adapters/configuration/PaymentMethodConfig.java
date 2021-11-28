package crowstream.microservice.payment.adapters.configuration;

import crowstream.microservice.payment.adapters.persistence.PaymentMethodJpaAdapter;
import crowstream.microservice.payment.adapters.persistence.repositories.GiftCardRepository;
import crowstream.microservice.payment.adapters.persistence.repositories.PaymentMethodRepository;
import crowstream.microservice.payment.application.ports.PaymentMethodPersistencePort;
import crowstream.microservice.payment.application.ports.PaymentMethodServicePort;
import crowstream.microservice.payment.application.services.PaymentMethodServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PaymentMethodConfig {
    @Bean
    public PaymentMethodPersistencePort paymentMethodPersistence(PaymentMethodRepository repository, GiftCardRepository giftCardRepository) {
        return new PaymentMethodJpaAdapter(repository, giftCardRepository);
    }

    @Bean
    public PaymentMethodServicePort paymentMethodService(PaymentMethodRepository repository, GiftCardRepository giftCardRepository) {
        return new PaymentMethodServiceImpl(paymentMethodPersistence(repository, giftCardRepository));
    }
}
