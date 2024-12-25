package org.example.carrent.service;



import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.util.UriComponentsBuilder;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class PayUService {

    @Value("${payu.clientId}")
    private String clientId;

    @Value("${payu.clientSecret}")
    private String clientSecret;

    @Value("${payu.paymentUrl}")
    private String paymentUrl;

    @Value("${payu.authUrl}")
    private String authUrl;

    private String getAuthToken() throws Exception {
        String authHeader = "Basic " + Base64.getEncoder()
                .encodeToString((clientId + ":" + clientSecret).getBytes(StandardCharsets.UTF_8));

        URI uri = UriComponentsBuilder.fromUriString(authUrl)
                .queryParam("grant_type", "client_credentials")
                .build().toUri();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(uri)
                .header("Authorization", authHeader)
                .POST(HttpRequest.BodyPublishers.noBody())
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Map<String, Object> jsonResponse = new ObjectMapper().readValue(response.body(), HashMap.class);
        return (String) jsonResponse.get("access_token");
    }

    public Map<String, String> initializePayment(String customerEmail, String description, double amount) throws Exception {
        String accessToken = getAuthToken();

        Map<String, Object> payload = new HashMap<>();
        payload.put("notifyUrl", "https://car-rental.pl:5173/payment/notify");
        payload.put("customerIp", "127.0.0.1");
        payload.put("merchantPosId", clientId);
        payload.put("description", description);
        payload.put("currencyCode", "PLN");
        payload.put("totalAmount", (int) (amount * 100));
        payload.put("buyer", Map.of(
                "email", customerEmail,
                "language", "en"
        ));

        String requestBody = new ObjectMapper().writeValueAsString(payload);

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(paymentUrl))
                .header("Authorization", "Bearer " + accessToken)
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                .build();

        HttpClient client = HttpClient.newHttpClient();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        Map<String, Object> jsonResponse = new ObjectMapper().readValue(response.body(), HashMap.class);
        String redirectUri = (String) ((Map<String, Object>) jsonResponse.get("redirectUri")).get("url");

        return Map.of(
                "paymentId", (String) jsonResponse.get("orderId"),
                "paymentUrl", redirectUri
        );
    }
}