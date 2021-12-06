package crowstream.microservice.payment.adapters.web;

import crowstream.microservice.payment.adapters.web.response.MessageResponse;
import crowstream.microservice.payment.application.domain.PaymentMethod;
import crowstream.microservice.payment.application.ports.PaymentMethodServicePort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payment-methods")
public class PaymentMethodController {
    private final PaymentMethodServicePort servicePort;

    public PaymentMethodController(PaymentMethodServicePort servicePort) {
        this.servicePort = servicePort;
    }

    @PostMapping("")
    public ResponseEntity<Object> addPaymentMethod(@RequestBody PaymentMethod paymentMethod) {
        PaymentMethod saved = this.servicePort.addPaymentMethod(paymentMethod);
        if (saved == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Invalid data for payment method creation"));
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getPaymentMethod(@PathVariable Long id) {
        PaymentMethod method = this.servicePort.getPaymentMethodById(id);
        if (method == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(String.format("Payment method with id %d does not exist", id)));
        }
        return ResponseEntity.status(HttpStatus.OK).body(method);
    }

    @GetMapping("")
    public ResponseEntity<Object> getPaymentMethods(@RequestParam(name = "account_id") String accountId) {
        List<PaymentMethod> methods = this.servicePort.getPaymentMethodsByAccountId(accountId);
        return ResponseEntity.status(HttpStatus.OK).body(methods);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePaymentMethod(@PathVariable Long id, @RequestBody PaymentMethod paymentMethod) {
        PaymentMethod method = this.servicePort.getPaymentMethodById(id);
        if (method == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(String.format("Payment method with id %d does not exist", id)));
        }
        if (!method.getAccountId().equals(paymentMethod.getAccountId()) && paymentMethod.getAccountId() != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse("Account id is not modifiable"));
        }
        paymentMethod.setId(method.getId());
        PaymentMethod updated = this.servicePort.updatePaymentMethod(paymentMethod);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePaymentMethod(@PathVariable Long id) {
        PaymentMethod method = this.servicePort.getPaymentMethodById(id);
        if (method == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(String.format("Payment method with id %d does not exist", id)));
        }
        this.servicePort.deletePaymentMethodById(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping("")
    public ResponseEntity<Object> deletePaymentMethods(@RequestParam(name = "account_id") String accountId) {
        this.servicePort.deletePaymentMethodsByAccountId(accountId);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
