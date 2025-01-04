package org.example.carrent.repository;

import org.example.carrent.dto.CustomerDTO;
import org.example.carrent.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    Optional<Customer> findByEmail(String email);

    Optional<Customer> findByKeycloakId(UUID keycloakId);
}
