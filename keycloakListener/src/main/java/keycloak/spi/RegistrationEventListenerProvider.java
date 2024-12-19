package keycloak.spi;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.keycloak.events.Event;
import org.keycloak.events.EventListenerProvider;
import org.keycloak.events.EventType;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.UserModel;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;

public class RegistrationEventListenerProvider implements EventListenerProvider {
    private final KeycloakSession session;
    private final HttpClient httpClient;
    private final ObjectMapper objectMapper;

    public RegistrationEventListenerProvider(KeycloakSession session) {
        this.session = session;
        this.httpClient = HttpClient.newHttpClient();
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void onEvent(Event event) {
        if (event.getType() == EventType.REGISTER) {
            RealmModel realm = session.getContext().getRealm();
            UserModel user = session.users().getUserById(realm, event.getUserId());


            String firstName = user.getFirstName();
            String lastName = user.getLastName();
            String email = user.getEmail();
            String phoneNumber = user.getFirstAttribute("phone_number");

            HashMap<String, Object> customerData = new HashMap<>();
            customerData.put("firstName", firstName);
            customerData.put("lastName", lastName);
            customerData.put("email", email);
            customerData.put("phoneNumber", phoneNumber);

            try {
                String requestBody = objectMapper.writeValueAsString(customerData);
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create("http://spring-backend:8081/customer-sync/register")
                        )
                        .header("Content-Type", "application/json")
                        .POST(HttpRequest.BodyPublishers.ofString(requestBody))
                        .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                if (response.statusCode() != 200) {
                    System.err.println("Failed to sync customer to Spring app. Status: " + response.statusCode());
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onEvent(org.keycloak.events.admin.AdminEvent adminEvent, boolean includeRepresentation) {
    }

    @Override
    public void close() {
    }
}