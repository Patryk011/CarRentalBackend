package org.example.carrent.service;

import org.example.carrent.dto.CarDTO;
import org.example.carrent.dto.CarModelDTO;
import org.example.carrent.entity.Car;
import org.example.carrent.exception.ResourceNotFoundException;
import org.example.carrent.mapper.CarMapper;
import org.example.carrent.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    private final CarRepository carRepository;
    private final CarMapper carMapper;

    public CarServiceImpl(CarRepository carRepository, CarMapper carMapper) {
        this.carRepository = carRepository;
        this.carMapper = carMapper;
    }

    @Override
    public CarDTO addCar(CarDTO carDTO) {
        Car car = carMapper.toEntity(carDTO);
        return carMapper.toDto(carRepository.save(car));
    }

    @Override
    public CarDTO findCarById(Long id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Car with id " + id + " not found"));
        return carMapper.toDto(car);
    }

    @Override
    public List<CarDTO> getAllCars() {
        return carMapper.toDto(carRepository.findAll());
    }
}