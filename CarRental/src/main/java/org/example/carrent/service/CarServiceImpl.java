package org.example.carrent.service;

import org.example.carrent.dto.CarDTO;
import org.example.carrent.entity.Car;
import org.example.carrent.enums.CarState;
import org.example.carrent.exception.ResourceNotFoundException;
import org.example.carrent.mapper.CarMapper;
import org.example.carrent.repository.CarRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        car.setState(CarState.AVAILABLE);
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

    @Override
    public List<CarDTO> findAvailableCars(LocalDate startDate, LocalDate endDate) {
        return carMapper.toDto(carRepository.findAvailableCarsInDateRange(startDate,endDate));
    }

    @Override
    public Integer checkOneCarAvailability(LocalDate startDate, LocalDate endDate, Long id) {
        return carRepository.checkOneCarAvailability(startDate, endDate, id);
    }


    @Override
    public CarDTO blockCar(Long id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Car with id " + id + " not found"));
        car.setState(CarState.BLOCKED);
        carRepository.save(car);
        return carMapper.toDto(car);
    }

    @Override
    public CarDTO unlockCar(Long id) {
        Car car = carRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Car with id " + id + " not found"));
        car.setState(CarState.AVAILABLE);
        carRepository.save(car);
        return carMapper.toDto(car);
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }
}