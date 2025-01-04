package org.example.carrent.Controllers;

import org.example.carrent.dto.CustomerDTO;
import org.example.carrent.entity.Customer;
import org.example.carrent.mapper.CustomerMapper;
import org.example.carrent.repository.CustomerRepository;
import org.example.carrent.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UsersController {

    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    public UsersController(CustomerService customerService,
                           CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @GetMapping("/me")
    public ResponseEntity<CustomerDTO> getCurrentUser(@AuthenticationPrincipal Jwt principal) {
        CustomerDTO dto = customerService.getCustomerByToken(principal);
        return ResponseEntity.ok(dto);
    }
}