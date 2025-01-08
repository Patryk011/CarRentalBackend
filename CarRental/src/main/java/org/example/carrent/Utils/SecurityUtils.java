package org.example.carrent.Utils;


import org.example.carrent.dto.CustomerDTO;
import org.example.carrent.entity.Customer;
import org.example.carrent.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class SecurityUtils {


    private final CustomerService customerService;

    @Autowired
    public SecurityUtils(CustomerService customerService) {
        this.customerService = customerService;
    }


    public CustomerDTO getAuthenticatedUser(Authentication authentication) {
        Jwt jwt = (Jwt) authentication.getPrincipal();
        return customerService.getCustomerByToken(jwt);
    }
}
