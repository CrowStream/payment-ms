package crowstream.microservice.payment.adapters.web;

import crowstream.microservice.payment.application.domain.GiftCard;
import crowstream.microservice.payment.application.ports.GiftCardServicePort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/gift-cards")
public class GiftCardController {
    private final GiftCardServicePort servicePort;

    public GiftCardController(GiftCardServicePort servicePort) {
        this.servicePort = servicePort;
    }

    @PostMapping("")
    public ResponseEntity<Object> addGiftCard(@RequestBody GiftCard giftCard) {
        GiftCard saved = this.servicePort.addGiftCard(giftCard);
        if (saved == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid data for gift card creation");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/id/{id}")
    public GiftCard getGiftCardById(@PathVariable Long id) {
        return this.servicePort.getGiftCardById(id);
    }

    @GetMapping("/{code}")
    public GiftCard getGiftCard(@PathVariable String code) {
        return this.servicePort.getGiftCardByCardCode(code);
    }

    @PutMapping("/{id}")
    public GiftCard updateGiftCard(@RequestBody GiftCard giftCard) {
        return this.servicePort.updateGiftCard(giftCard);
    }

    @DeleteMapping("/{id}")
    public void deleteGiftCard(@PathVariable Long id) {
        this.servicePort.deleteGiftCardById(id);
    }
}
