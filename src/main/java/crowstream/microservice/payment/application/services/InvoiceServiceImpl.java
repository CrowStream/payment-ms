package crowstream.microservice.payment.application.services;

import crowstream.microservice.payment.application.domain.Invoice;
import crowstream.microservice.payment.application.ports.InvoicePersistencePort;
import crowstream.microservice.payment.application.ports.InvoiceServicePort;

import java.util.List;

public class InvoiceServiceImpl implements InvoiceServicePort {
    private final InvoicePersistencePort invoicePersistencePort;

    public InvoiceServiceImpl(InvoicePersistencePort invoicePersistencePort) {
        this.invoicePersistencePort = invoicePersistencePort;
    }

    @Override
    public Invoice addInvoice(Invoice invoice) {
        return this.invoicePersistencePort.save(invoice);
    }

    @Override
    public Invoice getInvoiceById(Long id) {
        return this.invoicePersistencePort.findById(id);
    }

    @Override
    public List<Invoice> getInvoicesByAccountId(String accountId) {
        return this.invoicePersistencePort.findAllByAccountId(accountId);
    }

    @Override
    public Invoice updateInvoice(Invoice invoice) {
        return this.invoicePersistencePort.update(invoice);
    }

    @Override
    public void deleteInvoiceById(Long id) {
        this.invoicePersistencePort.deleteById(id);
    }

    @Override
    public void deleteInvoicesByAccountId(String accountId) {
        this.invoicePersistencePort.deleteAllByAccountId(accountId);
    }
}
