package crowstream.microservice.payment.adapters.web;

import crowstream.microservice.payment.application.domain.Invoice;
import crowstream.microservice.payment.application.ports.InvoiceServicePort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/invoices")
public class InvoiceController {
    private final InvoiceServicePort servicePort;

    public InvoiceController(InvoiceServicePort servicePort) {
        this.servicePort = servicePort;
    }

    @PostMapping("")
    public ResponseEntity<Object> addInvoice(@RequestBody Invoice invoice) {
        Invoice saved = this.servicePort.addInvoice(invoice);
        if (saved == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid data for invoice creation");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getInvoice(@PathVariable Long id) {
        Invoice invoice = this.servicePort.getInvoiceById(id);
        if (invoice == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Invoice with id %d does not exist", id));
        }
        return ResponseEntity.status(HttpStatus.OK).body(invoice);
    }

    @GetMapping("")
    public ResponseEntity<Object> getInvoices(@RequestParam("account_id") String accountId) {
        List<Invoice> invoices = this.servicePort.getInvoicesByAccountId(accountId);
        if (invoices.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No invoices associated with the account id provided");
        }
        return ResponseEntity.status(HttpStatus.OK).body(invoices);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateInvoice(@PathVariable Long id, @RequestBody Invoice invoice) {
        Invoice entity = this.servicePort.getInvoiceById(id);
        if (entity == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Invoice with id %d does not exist", id));
        }
        if (!entity.getAccountId().equals(invoice.getAccountId())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Account id is not modifiable");
        }
        invoice.setId(entity.getId());
        Invoice updated = this.servicePort.updateInvoice(invoice);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteInvoice(@PathVariable Long id) {
        Invoice entity = this.servicePort.getInvoiceById(id);
        if (entity == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Invoice with id %d does not exist", id));
        }
        this.servicePort.deleteInvoiceById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Invoice deleted");
    }

    @DeleteMapping("")
    public ResponseEntity<Object> deleteInvoices(@RequestParam("account_id") String accountId) {
        this.servicePort.deleteInvoicesByAccountId(accountId);
        if (this.servicePort.getInvoicesByAccountId(accountId).size() > 0) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error while deleting invoices");
        }
        return ResponseEntity.status(HttpStatus.OK).body("Invoices deleted");
    }
}
