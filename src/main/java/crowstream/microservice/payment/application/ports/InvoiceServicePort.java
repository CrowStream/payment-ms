package crowstream.microservice.payment.application.ports;

import crowstream.microservice.payment.application.domain.Invoice;

import java.util.List;

public interface InvoiceServicePort {
    Invoice addInvoice(Invoice invoice);
    Invoice getInvoiceById(Long id);
    List<Invoice> getInvoicesByAccountId(String accountId);
    Invoice updateInvoice(Invoice invoice);
    void deleteInvoiceById(Long id);
    void deleteInvoicesByAccountId(String accountId);
}
