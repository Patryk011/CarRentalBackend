package org.example.carrent.mapper;

import org.example.carrent.dto.CarDTO;
import org.example.carrent.entity.Car;
import org.springframework.stereotype.Component;

@Component
public class CarMapper {

    public static CarDTO toDto(Car car) {
        CarDTO dto = new CarDTO();
        dto.setId(car.getId());
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

    public static Car toEntity(CarDTO dto) {
        Car car = new Car();
        car.setId(dto.getId());
        car.setRegistrationNumber(dto.getRegistrationNumber());
        car.setPurchaseDate(dto.getPurchaseDate());
        car.setState(dto.getState());
        car.setVin(dto.getVin());
        car.setProductionYear(dto.getProductionYear());
        car.setColor(dto.getColor());
        car.setPricePerHour(dto.getPricePerHour());
        car.setTransmission(dto.getTransmission());
        car.setFuelType(dto.getFuelType());
        car.setSeats(dto.getSeats());
        car.setLastServiceDate(dto.getLastServiceDate());
        car.setNextServiceDate(dto.getNextServiceDate());
        car.setEngineCapacity(dto.getEngineCapacity());
        return car;
    }
}
