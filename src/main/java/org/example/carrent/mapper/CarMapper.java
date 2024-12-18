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
}
