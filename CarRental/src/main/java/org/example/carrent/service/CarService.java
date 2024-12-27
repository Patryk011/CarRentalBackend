package org.example.carrent.service;

import org.example.carrent.dto.CarDTO;
import org.example.carrent.dto.CarModelDTO;
import org.springframework.stereotype.Service;

import java.util.List;

public interface CarService {

    CarDTO addCar(CarDTO carDTO);

    CarDTO findCarById(Long id);

    List<CarDTO> getAllCars();
}