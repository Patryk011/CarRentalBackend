package org.example.carrent.mapper;

import org.example.carrent.dto.PaymentDTO;
import org.example.carrent.entity.Customer;
import org.example.carrent.entity.Payment;
import org.example.carrent.entity.Rental;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaymentMapper {

    private final CustomerMapper customerMapper;

    public PaymentMapper(CustomerMapper customerMapper) {
        this.customerMapper = customerMapper;
    }

    public PaymentDTO toDto(Payment payment) {
        return PaymentDTO.builder()
                .id(payment.getId())
                .payuOrderId(payment.getPayuOrderId())
                .customerId(payment.getCustomer() != null ? payment.getCustomer().getId() : null)
                .rentalId(payment.getRental() != null ? payment.getRental().getId() : null)
                .amount(payment.getAmount())
                .description(payment.getDescription())
                .paymentStatus(payment.getPaymentStatus())
                .createdAt(payment.getCreatedAt())
                .updatedAt(payment.getUpdatedAt())
                .build();
    }

    public List<PaymentDTO> toDto(List<Payment> payments) {
        return payments.stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    public Payment toEntity(PaymentDTO dto, Customer customer, Rental rental) {
        Payment payment = new Payment();
        payment.setId(dto.getId());
        payment.setPayuOrderId(dto.getPayuOrderId());
        payment.setCustomer(customer);
        payment.setRental(rental);
        payment.setAmount(dto.getAmount());
        payment.setDescription(dto.getDescription());
        payment.setPaymentStatus(dto.getPaymentStatus());
        payment.setCreatedAt(dto.getCreatedAt());
        payment.setUpdatedAt(dto.getUpdatedAt());
        return payment;
    }
}