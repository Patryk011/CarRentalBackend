package org.example.carrent.service;

import org.example.carrent.dto.CustomerDTO;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    List<CustomerDTO> getAllCustomers();

    CustomerDTO addCustomer(CustomerDTO customerDTO);

    CustomerDTO findByID(Long id);

    CustomerDTO updateCustomer(CustomerDTO customerDTO);

    CustomerDTO findByEmail(String email);

    void deleteCustomer(Long id);
}