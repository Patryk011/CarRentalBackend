package org.example.carrent.payuConfiguration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentCreationResponse {
    private String orderId;
    private String redirectUri;
}
