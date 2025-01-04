package org.example.carrent.service;

import org.example.carrent.dto.CustomerDTO;
import org.example.carrent.entity.Customer;
import org.example.carrent.exception.ResourceNotFoundException;
import org.example.carrent.mapper.CustomerMapper;
import org.example.carrent.repository.CustomerRepository;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public CustomerServiceImpl(CustomerRepository customerRepository,CustomerMapper customerMapper) {
        this.customerRepository = customerRepository;
        this.customerMapper = customerMapper;
    }

    @Override
    public List<CustomerDTO> getAllCustomers() {
       return customerMapper.toDto(customerRepository.findAll());
    }
    @Override
    public CustomerDTO addCustomer(CustomerDTO customerDTO) {
        Customer customer = customerMapper.toEntity(customerDTO);
        return customerMapper.toDto(customerRepository.save(customer));
    }

    @Override
    public CustomerDTO findByID(Long id) {
        Customer customer = customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer with id " + id + " not found"));
        return customerMapper.toDto(customer);
    }

    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {
        customerRepository.findById(customerDTO.getId()).orElseThrow(() -> new ResourceNotFoundException("Customer with id " + customerDTO.getId() + " not found"));
        Customer updatedCustomer = customerMapper.toEntity(customerDTO);
        Customer savedCustomer = customerRepository.save(updatedCustomer);
        return customerMapper.toDto(savedCustomer);
    }

    @Override
    public CustomerDTO findByEmail(String email) {
        Customer customer = customerRepository.findByEmail(email).orElseThrow(() -> new ResourceNotFoundException("Customer with email " + email + " not found"));;
        return customerMapper.toDto(customer);
    }

    @Override
    public void deleteCustomer(Long id) {
        customerRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Customer with id " + id + " not found"));
        customerRepository.deleteById(id);
    }

    @Override
    public CustomerDTO getCustomerByToken(Jwt principal) {
        String subAsString = principal.getClaimAsString("sub");
        if (subAsString == null) {
            throw new RuntimeException("No 'sub' claim found in the token!");
        }

        UUID subAsUuid;
        try {
            subAsUuid = UUID.fromString(subAsString);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Keycloak 'sub' is not a valid UUID: " + subAsString);
        }

        Customer customer = customerRepository.findByKeycloakId(subAsUuid)
                .orElseThrow(() -> new RuntimeException("No matching customer found for sub=" + subAsString));

        return customerMapper.toDto(customer);
    }


}
