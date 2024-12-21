package org.example.carrent.mapper;

import org.example.carrent.dto.CarDTO;
import org.example.carrent.entity.Car;
import org.example.carrent.entity.CarModel;
import org.example.carrent.exception.ResourceNotFoundException;
import org.example.carrent.repository.CarModelRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CarMapper {
    private final CarModelRepository carModelRepository;

    public CarMapper(CarModelRepository carModelRepository) {
        this.carModelRepository = carModelRepository;
    }

    public static CarDTO toDto(Car car) {
        CarDTO dto = new CarDTO();
        dto.setId(car.getId());
        dto.setCarModelId(car.getCarModel().getId());
        dto.setRegistrationNumber(car.getRegistrationNumber());
        dto.setPurchaseDate(car.getPurchaseDate());
        dto.setState(car.getState());
        dto.setVin(car.getVin());
        dto.setProductionYear(car.getProductionYear());
        dto.setColor(car.getColor());
        dto.setPricePerHour(car.getPricePerHour());
        dto.setTransmission(car.getTransmission());
        dto.setFuelType(car.getFuelType());
        dto.setSeats(car.getSeats());
        dto.setLastServiceDate(car.getLastServiceDate());
        dto.setNextServiceDate(car.getNextServiceDate());
        dto.setEngineCapacity(car.getEngineCapacity());
        return dto;
    }

    public Car toEntity(CarDTO dto) {
        Car entity = new Car();
        CarModel carModel = carModelRepository.findById(dto.getCarModelId()).orElseThrow(() -> new ResourceNotFoundException("Car model with id " + dto.getCarModelId() + " not found"));
        entity.setId(dto.getId());
        entity.setRegistrationNumber(dto.getRegistrationNumber());
        entity.setPurchaseDate(dto.getPurchaseDate());
        entity.setState(dto.getState());
        entity.setVin(dto.getVin());
        entity.setProductionYear(dto.getProductionYear());
        entity.setColor(dto.getColor());
        entity.setPricePerHour(dto.getPricePerHour());
        entity.setTransmission(dto.getTransmission());
        entity.setFuelType(dto.getFuelType());
        entity.setSeats(dto.getSeats());
        entity.setLastServiceDate(dto.getLastServiceDate());
        entity.setNextServiceDate(dto.getNextServiceDate());
        entity.setEngineCapacity(dto.getEngineCapacity());
        return entity;
    }

    public static List<CarDTO> toDto(List<Car> cars){
        return cars.stream().map(CarMapper::toDto)
                .collect(Collectors.toList());
    }
}