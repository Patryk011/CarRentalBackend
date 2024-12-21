package org.example.carrent.mapper;

import org.example.carrent.entity.Customer;
import org.example.carrent.dto.CustomerDTO;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerMapper {

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
        return dto;
    }

    public static List<CustomerDTO> toDto(List<Customer> customers) {
        return customers.stream().map(CustomerMapper::toDto)
                .collect(Collectors.toList());
    }

    public static Customer toEntity(CustomerDTO dto) {
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
        return entity;
    }
}
