package org.example.carrent.service;


import org.example.carrent.Utils.MoneyUtil;
import org.example.carrent.entity.Rental;
import org.example.carrent.payuConfiguration.PayUConfig;
import org.example.carrent.payuConfiguration.PaymentCreationResponse;
import org.example.carrent.dto.PaymentDTO;
import org.example.carrent.entity.Customer;
import org.example.carrent.entity.Payment;
import org.example.carrent.mapper.PaymentMapper;
import org.example.carrent.repository.CustomerRepository;
import org.example.carrent.repository.PaymentRepository;
import org.example.carrent.repository.RentalRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PayUConfig payUConfig;
    private final PaymentRepository paymentRepository;
    private final CustomerRepository customerRepository;
    private final RentalRepository rentalRepository;
    private final PaymentMapper paymentMapper;
    private final WebClient webClient = WebClient.builder().build();

    public PaymentServiceImpl(PayUConfig payUConfig,
                              PaymentRepository paymentRepository,
                              CustomerRepository customerRepository,
                              PaymentMapper paymentMapper, RentalRepository rentalRepository) {
        this.payUConfig = payUConfig;
        this.paymentRepository = paymentRepository;
        this.rentalRepository = rentalRepository;
        this.customerRepository = customerRepository;
        this.paymentMapper = paymentMapper;
    }

    @Override
    public PaymentCreationResponse createPayment(PaymentDTO request) {

        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        Rental rental = rentalRepository.findById(request.getRentalId()).orElseThrow(() -> new IllegalArgumentException("Rental not found"));

        String token = obtainOAuthToken();

        Map<String, Object> payUOrderRequest = buildPayUOrderRequest(request, customer);

        Map<String, Object> payUResponse = webClient.post()
                .uri(payUConfig.getPaymentUrl())
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(payUOrderRequest)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                .block();

        String payuOrderId = (String) payUResponse.get("orderId");
        String redirectUri = (String) payUResponse.get("redirectUri");




        Payment payment = paymentMapper.toEntity(request, customer, rental);
        payment.setCreatedAt(LocalDateTime.now());
        payment.setPaymentStatus("PENDING");
        payment.setPayuOrderId(payuOrderId);

        paymentRepository.save(payment);

        return new PaymentCreationResponse(payuOrderId, redirectUri);
    }


    private String obtainOAuthToken() {
        String credentials = payUConfig.getClientId() + ":" + payUConfig.getClientSecret();
        String encodedCredentials = Base64.getEncoder().encodeToString(credentials.getBytes(StandardCharsets.UTF_8));

        Map<String, String> tokenResponse = webClient.post()
                .uri(payUConfig.getAuthUrl())
                .header(HttpHeaders.AUTHORIZATION, "Basic " + encodedCredentials)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .bodyValue("grant_type=client_credentials")
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, String>>() {})
                .block();

        return tokenResponse != null ? tokenResponse.get("access_token") : null;
    }


    private Map<String, Object> buildPayUOrderRequest(PaymentDTO request, Customer customer)

    {

        BigDecimal amount = MoneyUtil.toStoredFormat(request.getAmount());


        Map<String, Object> payUOrder = new HashMap<>();
        payUOrder.put("notifyUrl", "http://localhost:8081/api/payu/notify");
        payUOrder.put("customerIp", "127.0.0.1");
        payUOrder.put("merchantPosId", payUConfig.getClientId());
        payUOrder.put("description", request.getDescription());
        payUOrder.put("currencyCode", "PLN");
        payUOrder.put("totalAmount", amount.longValue());
        payUOrder.put("extOrderId", UUID.randomUUID().toString());

        Map<String, String> buyer = new HashMap<>();
        buyer.put("email", customer.getEmail());
        buyer.put("language", "pl");
        payUOrder.put("buyer", buyer);

        List<Map<String, Object>> products = new ArrayList<>();
        Map<String, Object> product = new HashMap<>();
        product.put("name", "Car rental");
        product.put("unitPrice", amount.longValue());
        product.put("quantity", "1");
        products.add(product);
        payUOrder.put("products", products);

        return payUOrder;
    }

    }
