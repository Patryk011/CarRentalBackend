package org.example.carrent.dto;

import lombok.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private Date birthDate;
    private String licenseNumber;
    private String address;
    private Long registrationDate;
    private Long discountId;
}
