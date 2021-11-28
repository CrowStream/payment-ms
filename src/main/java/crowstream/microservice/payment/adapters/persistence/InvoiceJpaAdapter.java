package crowstream.microservice.payment.adapters.persistence;

import crowstream.microservice.payment.adapters.persistence.entities.InvoiceEntity;
import crowstream.microservice.payment.adapters.persistence.entities.PaymentEntity;
import crowstream.microservice.payment.adapters.persistence.mappers.InvoiceMapper;
import crowstream.microservice.payment.adapters.persistence.repositories.InvoiceRepository;
import crowstream.microservice.payment.adapters.persistence.repositories.PaymentRepository;
import crowstream.microservice.payment.application.domain.Invoice;
import crowstream.microservice.payment.application.ports.InvoicePersistencePort;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InvoiceJpaAdapter implements InvoicePersistencePort {
    private final InvoiceRepository repository;
    private final PaymentRepository paymentRepository;
    private final InvoiceMapper mapper;

    public InvoiceJpaAdapter(InvoiceRepository repository, PaymentRepository paymentRepository) {
        this.repository = repository;
        this.paymentRepository = paymentRepository;
        this.mapper = new InvoiceMapper();
    }

    @Override
    public Invoice save(Invoice invoice) {
        InvoiceEntity entity;
        if (invoice.getPaymentId() != null) {
            Optional<PaymentEntity> optionalPayment = this.paymentRepository.findById(invoice.getId());
            entity = this.mapper.pojoToEntity(invoice, optionalPayment.orElse(null));
        } else {
            entity = this.mapper.pojoToEntity(invoice, null);
        }
        InvoiceEntity saved = this.repository.save(entity);
        return this.mapper.entityToPojo(saved);
    }

    @Override
    public Invoice findById(Long id) {
        Optional<InvoiceEntity> entity = this.repository.findById(id);
        return entity.map(this.mapper::entityToPojo).orElse(null);
    }

    @Override
    public List<Invoice> findAllByAccountId(String accountId) {
        List<InvoiceEntity> entities = this.repository.findAllByAccountId(accountId);
        List<Invoice> invoices = new ArrayList<>();
        for (InvoiceEntity entity : entities) {
            invoices.add(this.mapper.entityToPojo(entity));
        }
        return invoices;
    }

    @Override
    public Boolean existsById(Long id) {
        return this.repository.existsById(id);
    }

    @Override
    public Invoice update(Invoice invoice) {
        if (!this.repository.existsById(invoice.getId())) {
            return null;
        }
        return this.save(invoice);
    }

    @Override
    public void deleteById(Long id) {
        if (!this.repository.existsById(id)) {
            return;
        }
        this.repository.deleteById(id);
    }

    @Override
    public void deleteAllByAccountId(String accountId) {
        if (this.repository.countByAccountId(accountId) < 1) {
            return;
        }
        this.repository.deleteAllByAccountId(accountId);
    }
}
