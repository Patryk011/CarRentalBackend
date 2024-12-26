package org.example.carrent.service;


import org.example.carrent.configuration.PayUConfig;
import org.example.carrent.configuration.PaymentCreationResponse;
import org.example.carrent.dto.PaymentDTO;
import org.example.carrent.entity.Customer;
import org.example.carrent.entity.Payment;
import org.example.carrent.mapper.PaymentMapper;
import org.example.carrent.repository.CustomerRepository;
import org.example.carrent.repository.PaymentRepository;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final PayUConfig payUConfig;
    private final PaymentRepository paymentRepository;
    private final CustomerRepository customerRepository;
    private final PaymentMapper paymentMapper;
    private final WebClient webClient = WebClient.builder().build();

    public PaymentServiceImpl(PayUConfig payUConfig,
                              PaymentRepository paymentRepository,
                              CustomerRepository customerRepository,
                              PaymentMapper paymentMapper) {
        this.payUConfig = payUConfig;
        this.paymentRepository = paymentRepository;
        this.customerRepository = customerRepository;
        this.paymentMapper = paymentMapper;
    }

    @Override
    public PaymentCreationResponse createPayment(PaymentDTO request) {
        String token = obtainOAuthToken();

        Map<String, Object> payUOrderRequest = buildPayUOrderRequest(request);

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


        Customer customer = customerRepository.findByEmail(request.getCustomerEmail())
                .orElseThrow(() -> new IllegalArgumentException("Customer not found"));

        Payment payment = paymentMapper.toEntity(request, customer);
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


    private Map<String, Object> buildPayUOrderRequest(PaymentDTO request) {
        Map<String, Object> payUOrder = new HashMap<>();

        payUOrder.put("notifyUrl", "http://localhost:8081/api/payu/notify");
        payUOrder.put("customerIp", "127.0.0.1");
        payUOrder.put("merchantPosId", payUConfig.getClientId());
        payUOrder.put("description", request.getDescription());
        payUOrder.put("currencyCode", "PLN");
        payUOrder.put("totalAmount", (long) (request.getAmount() * 100));
        payUOrder.put("extOrderId", UUID.randomUUID().toString());

        Map<String, String> buyer = new HashMap<>();
        buyer.put("email", request.getCustomerEmail());
        buyer.put("language", "pl");
        payUOrder.put("buyer", buyer);

        List<Map<String, Object>> products = new ArrayList<>();
        Map<String, Object> product = new HashMap<>();
        product.put("name", "Car rental");
        product.put("unitPrice", (long) (request.getAmount() * 100));
        product.put("quantity", "1");
        products.add(product);
        payUOrder.put("products", products);

        return payUOrder;
    }
}