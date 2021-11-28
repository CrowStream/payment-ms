package crowstream.microservice.payment.application.ports;

import crowstream.microservice.payment.application.domain.Invoice;

import java.util.List;

public interface InvoicePersistencePort {
    Invoice save(Invoice invoice);
    Invoice findById(Long id);
    List<Invoice> findAllByAccountId(String accountId);
    Boolean existsById(Long id);
    Invoice update(Invoice invoice);
    void deleteById(Long id);
    void deleteAllByAccountId(String accountId);
}
