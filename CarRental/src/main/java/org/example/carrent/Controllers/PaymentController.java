package org.example.carrent.Controllers;

import org.example.carrent.configuration.PaymentCreationResponse;
import org.example.carrent.dto.PaymentDTO;
import org.example.carrent.service.PaymentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentCreationResponse> createPayment(@RequestBody PaymentDTO paymentDTO) {
        PaymentCreationResponse paymentResponse = paymentService.createPayment(paymentDTO);
        return new ResponseEntity<>(paymentResponse, HttpStatus.CREATED);
    }
}
