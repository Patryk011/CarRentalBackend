package org.example.carrent.Controllers;


import org.example.carrent.dto.PaymentDTO;
import org.example.carrent.entity.Payment;
import org.example.carrent.repository.PaymentRepository;
import org.example.carrent.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/payu")
public class PayUController {

    private final PaymentRepository paymentRepository;

    public PayUController(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @PostMapping("/notify")
    public ResponseEntity<Void> payuWebhook(@RequestBody Map<String, Object> payload) {
        Map<String, Object> order = (Map<String, Object>) payload.get("order");
        String orderId = (String) order.get("orderId");
        String status = (String) order.get("status");

        System.out.println("Received PayU Webhook Payload: " + payload);

        Payment payment = paymentRepository.findAll()
                .stream()
                .filter(p -> orderId.equals(p.getPayuOrderId()))
                .findFirst()
                .orElse(null);

        if (payment != null) {
            payment.setPaymentStatus(status);
            payment.setUpdatedAt(LocalDateTime.now());
            paymentRepository.save(payment);
        }

        return ResponseEntity.ok().build();
    }
}
