package org.example.carrent.payuConfiguration;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "payu")
@Getter
@Setter
public class PayUConfig {

    private String clientId;
    private String clientSecret;
    private String authUrl;
    private String paymentUrl;
}
