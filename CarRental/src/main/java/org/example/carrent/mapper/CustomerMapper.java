package org.example.carrent.mapper;

import org.example.carrent.entity.Customer;
import org.example.carrent.dto.CustomerDTO;
import org.example.carrent.entity.Discount;
import org.example.carrent.exception.ResourceNotFoundException;
import org.example.carrent.repository.DiscountRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {
    private final DiscountRepository discountRepository;

    public CustomerMapper(DiscountRepository discountRepository) {
        this.discountRepository = discountRepository;
    }

    public static CustomerDTO toDto(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getId());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setEmail(customer.getEmail());
        dto.setPhoneNumber(String.valueOf(customer.getPhoneNumber()));
        dto.setBirthDate(customer.getBirthDate());
        dto.setLicenseNumber(String.valueOf(customer.getLicenseNumber()));
        dto.setAddress(customer.getAddress());
        dto.setRegistrationDate(customer.getRegistrationDate());
        dto.setKeycloakId(customer.getKeycloak_id());
        return dto;
    }

    public static List<CustomerDTO> toDto(List<Customer> customers) {
        return customers.stream().map(CustomerMapper::toDto)
                .collect(Collectors.toList());
    }

    public Customer toEntity(CustomerDTO dto) {
        Customer entity = new Customer();
        entity.setId(dto.getId());
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setBirthDate(dto.getBirthDate());
        entity.setLicenseNumber(dto.getLicenseNumber());
        entity.setAddress(dto.getAddress());
        entity.setRegistrationDate(dto.getRegistrationDate());
        entity.setKeycloak_id(dto.getKeycloakId());
        return entity;
    }
}
