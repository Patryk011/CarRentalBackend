package org.example.carrent.service;

import org.example.carrent.dto.CarDTO;
import org.example.carrent.dto.CarModelDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface CarService {

    CarDTO addCar(CarDTO carDTO);

    CarDTO findCarById(Long id);

    List<CarDTO> getAllCars();

    List<CarDTO> findAvailableCars(LocalDate startDate, LocalDate endDate);

    Integer checkOneCarAvailability(LocalDate startDate, LocalDate endDate, Long id);

    CarDTO blockCar(Long id);

    CarDTO unlockCar(Long id);
}