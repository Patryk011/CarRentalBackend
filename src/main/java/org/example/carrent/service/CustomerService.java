package org.example.carrent.service;

import org.example.carrent.dto.CustomerDTO;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> getAllCustomers();
    CustomerDTO addCustomer(CustomerDTO customerDTO);
}
