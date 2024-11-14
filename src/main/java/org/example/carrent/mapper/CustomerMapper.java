package org.example.carrent.mapper;

import org.example.carrent.entity.Customer;
import org.example.carrent.dto.CustomerDTO;
import org.springframework.stereotype.Component;

import java.util.List;

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
        return dto;
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
        return entity;
    }
}
