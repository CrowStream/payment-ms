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

    @GetMapping("/{id}")
    public ResponseEntity<Object> getGiftCardById(@PathVariable Long id) {
        GiftCard giftCard = this.servicePort.getGiftCardById(id);
        if (giftCard == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Gift card with id %d does not exist", id));
        }
        return ResponseEntity.status(HttpStatus.OK).body(giftCard);
    }

    @GetMapping("")
    public ResponseEntity<Object> getGiftCard(@RequestParam(name = "card_code") String code) {
        GiftCard giftCard = this.servicePort.getGiftCardByCardCode(code);
        if (giftCard == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No gift cards associated with the code provided");
        }
        return ResponseEntity.status(HttpStatus.OK).body(giftCard);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateGiftCard(@PathVariable Long id, @RequestBody GiftCard giftCard) {
        if (this.servicePort.getGiftCardById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Gift card with id %d does not exist", id));
        }
        giftCard.setId(id);
        GiftCard updated = this.servicePort.updateGiftCard(giftCard);
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteGiftCard(@PathVariable Long id) {
        if (this.servicePort.getGiftCardById(id) == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(String.format("Gift card with id %d does not exist", id));
        }
        this.servicePort.deleteGiftCardById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Gift card deleted");
    }
}
