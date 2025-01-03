package org.example.carrent.service;

import org.example.carrent.dto.RentalDTO;
import org.example.carrent.entity.Rental;
import org.example.carrent.enums.RentalStatus;
import org.example.carrent.exception.ResourceNotFoundException;
import org.example.carrent.mapper.RentalMapper;
import org.example.carrent.repository.RentalRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RentalServiceImpl implements RentalService {
    private final RentalRepository rentalRepository;
    private final RentalMapper rentalMapper;

    public RentalServiceImpl(RentalRepository rentalRepository, RentalMapper rentalMapper) {
        this.rentalRepository = rentalRepository;
        this.rentalMapper = rentalMapper;
    }


    @Override
    public RentalDTO addRental(RentalDTO rentalDTO) {
        Rental rental = rentalMapper.toEntity(rentalDTO);
        rental.setStatus(RentalStatus.CONFIRMED);
        return rentalMapper.toDto(rentalRepository.save(rental));
    }

    @Override
    public RentalDTO findRentalById(Long id) {
        Rental rental = rentalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Rental with id " + id + " not found"));
        return rentalMapper.toDto(rental);
    }

    @Override
    public List<RentalDTO> getAllRentals() {
        return rentalMapper.toDto(rentalRepository.findAll());
    }

    @Override
    public RentalDTO cancelRental(Long id) {
        Rental rental = rentalRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Rental with id " + id + " not found"));
        if (rental.getStatus() == RentalStatus.FINISHED) {
            throw new IllegalStateException("Cannot cancel a finished rental.");
        }
        rental.setStatus(RentalStatus.CANCELLED);
        rentalRepository.save(rental);
        return rentalMapper.toDto(rental);
    }


}
