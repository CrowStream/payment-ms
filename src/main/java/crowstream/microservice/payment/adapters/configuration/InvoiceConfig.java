package crowstream.microservice.payment.adapters.configuration;

import crowstream.microservice.payment.adapters.persistence.InvoiceJpaAdapter;
import crowstream.microservice.payment.adapters.persistence.repositories.InvoiceRepository;
import crowstream.microservice.payment.adapters.persistence.repositories.PaymentRepository;
import crowstream.microservice.payment.application.ports.InvoicePersistencePort;
import crowstream.microservice.payment.application.ports.InvoiceServicePort;
import crowstream.microservice.payment.application.services.InvoiceServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InvoiceConfig {
    @Bean
    public InvoicePersistencePort invoicePersistence(InvoiceRepository repository, PaymentRepository paymentRepository) {
        return new InvoiceJpaAdapter(repository, paymentRepository);
    }

    @Bean
    public InvoiceServicePort invoiceService(InvoiceRepository repository, PaymentRepository paymentRepository) {
        return new InvoiceServiceImpl(invoicePersistence(repository, paymentRepository));
    }
}
