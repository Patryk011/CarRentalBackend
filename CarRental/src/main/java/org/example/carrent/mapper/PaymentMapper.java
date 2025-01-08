package org.example.carrent.mapper;

import org.example.carrent.dto.PaymentDTO;
import org.example.carrent.entity.Customer;
import org.example.carrent.entity.Payment;
import org.example.carrent.entity.Rental;
import org.example.carrent.exception.ResourceNotFoundException;
import org.example.carrent.repository.CustomerRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PaymentMapper {

    private final CustomerMapper customerMapper;
    private final CustomerRepository customerRepository;

    public PaymentMapper(CustomerMapper customerMapper, CustomerRepository customerRepository) {
        this.customerMapper = customerMapper;
        this.customerRepository = customerRepository;
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

    public Payment toEntity(PaymentDTO dto, Rental rental) {
        Payment payment = new Payment();
        Customer customer = customerRepository.findById(dto.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Customer with id " + dto.getCustomerId() + " not found"));
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