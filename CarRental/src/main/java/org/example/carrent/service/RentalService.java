package org.example.carrent.service;

import org.example.carrent.dto.RentalDTO;

import java.util.List;

public interface RentalService {
    RentalDTO addRental(RentalDTO rentalDTO);
    RentalDTO findRentalById(Long id);
    List<RentalDTO> getAllRentals();
    RentalDTO cancelRental(Long id);
}
