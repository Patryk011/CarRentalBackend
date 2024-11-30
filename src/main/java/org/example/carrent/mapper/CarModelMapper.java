package org.example.carrent.mapper;

import org.example.carrent.dto.CarModelDTO;
import org.example.carrent.entity.CarBrand;
import org.example.carrent.entity.CarModel;
import org.example.carrent.exception.ResourceNotFoundException;
import org.example.carrent.repository.CarBrandRepository;
import org.springframework.stereotype.Component;

@Component
public class CarModelMapper {
    private final CarBrandRepository carBrandRepository;

    public CarModelMapper(CarBrandRepository carBrandRepository) {
        this.carBrandRepository = carBrandRepository;
    }

    public static CarModelDTO toDto(CarModel carModel) {
        CarModelDTO dto = new CarModelDTO();
        dto.setId(carModel.getId());
        dto.setModel(carModel.getModel());
        dto.setCarBrandId(carModel.getCarBrand().getId());

        return dto;
    }

    public CarModel toEntity(CarModelDTO dto) {
        CarModel entity = new CarModel();
        CarBrand carBrand = carBrandRepository.findById(dto.getCarBrandId()).orElseThrow(() -> new ResourceNotFoundException("Car brand with id " + dto.getCarBrandId() + " not found"));
        entity.setId(dto.getId());
        entity.setModel(dto.getModel());
        entity.setCarBrand(carBrand);

        return entity;
    }
}
