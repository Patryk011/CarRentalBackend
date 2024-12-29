package org.example.carrent.mapper;

import org.example.carrent.dto.CarBrandDTO;
import org.example.carrent.dto.CarModelDTO;
import org.example.carrent.entity.CarBrand;
import org.example.carrent.entity.CarModel;
import org.example.carrent.exception.ResourceNotFoundException;
import org.example.carrent.repository.CarBrandRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

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
        dto.setCarBrandName(carModel.getCarBrand().getBrand());

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

    public static List<CarModelDTO> toDto(List<CarModel> carModels) {
        return carModels.stream().map(CarModelMapper::toDto)
                .collect(Collectors.toList());
    }
}
