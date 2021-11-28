package crowstream.microservice.payment.adapters.web;

import crowstream.microservice.payment.application.domain.Payment;
import crowstream.microservice.payment.application.ports.PaymentServicePort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {
    private final PaymentServicePort servicePort;

    public PaymentController(PaymentServicePort servicePort) {
        this.servicePort = servicePort;
    }

    @PostMapping("")
    public ResponseEntity<Object> addPayment(@RequestBody Payment payment) {
        Payment saved = this.servicePort.addPayment(payment);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPayment(@PathVariable Long id) {
        Payment payment = this.servicePort.getPaymentById(id);
        if (payment == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Payment with id %d does not exist", id));
        }
        return ResponseEntity.status(HttpStatus.OK).body(payment);
    }

    @GetMapping("")
    public ResponseEntity<Object> getPayments(@RequestParam String accountId) {
        List<Payment> payments = this.servicePort.getPaymentsByAccountId(accountId);
        if (payments.size() == 0) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No payment methods associated with the account id provided");
        }
        return ResponseEntity.status(HttpStatus.OK).body(payments);
    }
}
