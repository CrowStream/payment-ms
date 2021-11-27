package crowstream.microservice.payment.adapters.configuration;

import crowstream.microservice.payment.adapters.persistence.GiftCardJpaAdapter;
import crowstream.microservice.payment.adapters.persistence.repositories.GiftCardRepository;
import crowstream.microservice.payment.application.ports.GiftCardPersistencePort;
import crowstream.microservice.payment.application.ports.GiftCardServicePort;
import crowstream.microservice.payment.application.services.GiftCardServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GiftCardConfig {
    @Bean
    public GiftCardPersistencePort giftCardPersistence(GiftCardRepository repository) {
        return new GiftCardJpaAdapter(repository);
    }

    @Bean
    public GiftCardServicePort giftCardService(GiftCardRepository repository) {
        return new GiftCardServiceImpl(giftCardPersistence(repository));
    }
}
