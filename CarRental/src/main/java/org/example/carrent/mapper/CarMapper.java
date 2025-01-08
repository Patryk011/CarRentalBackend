package org.example.carrent.mapper;

import org.example.carrent.Utils.MoneyUtil;
import org.example.carrent.dto.CarDTO;
import org.example.carrent.entity.Car;
import org.example.carrent.entity.CarModel;
import org.example.carrent.exception.ResourceNotFoundException;
import org.example.carrent.repository.CarModelRepository;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
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
        dto.setCarModelName(car.getCarModel().getModel());
        dto.setCarBrandName(car.getCarModel().getCarBrand().getBrand());
        dto.setRegistrationNumber(car.getRegistrationNumber());
        dto.setState(car.getState());
        dto.setProductionYear(car.getProductionYear());
        dto.setColor(car.getColor());
        dto.setPricePerDay(MoneyUtil.toDisplayFormat(BigDecimal.valueOf(car.getPricePerDay())));
        dto.setTransmission(car.getTransmission());
        dto.setFuelType(car.getFuelType());
        dto.setSeats(car.getSeats());
        dto.setEngineCapacity(car.getEngineCapacity());
        return dto;
    }

    public Car toEntity(CarDTO dto) {
        Car entity = new Car();
        CarModel carModel = carModelRepository.findById(dto.getCarModelId()).orElseThrow(() -> new ResourceNotFoundException("Car model with id " + dto.getCarModelId() + " not found"));
        entity.setCarModel(carModel);
        entity.setId(dto.getId());
        entity.setRegistrationNumber(dto.getRegistrationNumber());
        entity.setState(dto.getState());
        entity.setProductionYear(dto.getProductionYear());
        entity.setColor(dto.getColor());
        entity.setPricePerDay(MoneyUtil.toStoredFormat(dto.getPricePerDay()).longValue());
        entity.setTransmission(dto.getTransmission());
        entity.setFuelType(dto.getFuelType());
        entity.setSeats(dto.getSeats());
        entity.setEngineCapacity(dto.getEngineCapacity());
        return entity;
    }

    public static List<CarDTO> toDto(List<Car> cars){
        return cars.stream().map(CarMapper::toDto)
                .collect(Collectors.toList());
    }
}