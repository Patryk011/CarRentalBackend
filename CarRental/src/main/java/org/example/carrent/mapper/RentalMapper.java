package org.example.carrent.mapper;

import org.example.carrent.dto.RentalDTO;
import org.example.carrent.entity.Car;
import org.example.carrent.entity.Customer;
import org.example.carrent.entity.Rental;
import org.example.carrent.exception.ResourceNotFoundException;
import org.example.carrent.repository.CarRepository;
import org.example.carrent.repository.CustomerRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RentalMapper {
    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;


    public RentalMapper(CustomerRepository customerRepository, CarRepository carRepository) {
        this.customerRepository = customerRepository;
        this.carRepository = carRepository;
    }

    public static RentalDTO toDto(Rental rental) {
        RentalDTO dto = new RentalDTO();
        dto.setId(rental.getId());
        dto.setCustomerId(rental.getCustomer().getId());
        dto.setDiscountPercentage(rental.getCustomer().getDiscountPercentage());
        dto.setCarId(rental.getCar().getId());
        dto.setStartDate(rental.getStartDate());
        dto.setFinishDate(rental.getFinishDate());
        dto.setTotalCost(rental.getTotalCost());
        dto.setStatus(rental.getStatus());
        dto.setCarBrand(rental.getCar().getCarModel().getCarBrand().getBrand());
        dto.setCarModel(rental.getCar().getCarModel().getModel());
        dto.setRegistrationNumber(rental.getCar().getRegistrationNumber());
        return dto;
    }

    public Rental toEntity(RentalDTO dto) {
        Rental entity = new Rental();
        Customer customer = customerRepository.findById(dto.getCustomerId()).orElseThrow(() -> new ResourceNotFoundException("Customer with id " + dto.getCustomerId() + " not found"));
        Car car = carRepository.findById(dto.getCarId()).orElseThrow(() -> new ResourceNotFoundException("Car with id " + dto.getCarId() + " not found"));
        entity.setId(dto.getId());
        entity.setCustomer(customer);
        entity.setCar(car);
        entity.setStatus(dto.getStatus());
        entity.setStartDate(dto.getStartDate());
        entity.setTotalCost(dto.getTotalCost());
        entity.setFinishDate(dto.getFinishDate());
        return entity;
    }

    public static List<RentalDTO> toDto(List<Rental> rentals) {
        return rentals.stream().map(RentalMapper::toDto)
                .collect(Collectors.toList());
    }
}
