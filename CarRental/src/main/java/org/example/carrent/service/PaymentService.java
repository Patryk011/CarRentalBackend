package org.example.carrent.service;

import org.example.carrent.payuConfiguration.PaymentCreationResponse;
import org.example.carrent.dto.PaymentDTO;

public interface PaymentService {
    PaymentCreationResponse createPayment(PaymentDTO request);
}
