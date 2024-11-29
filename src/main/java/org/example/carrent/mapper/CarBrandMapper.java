package org.example.carrent.mapper;

import org.example.carrent.dto.CarBrandDTO;
import org.example.carrent.entity.CarBrand;
import org.springframework.stereotype.Component;

@Component
public class CarBrandMapper {

    public static CarBrandDTO toDto(CarBrand carBrand) {
        CarBrandDTO dto = new CarBrandDTO();
        dto.setId(carBrand.getId());
        dto.setBrand(carBrand.getBrand());

        return dto;
    }

    public static CarBrand toEntity(CarBrandDTO dto) {
        CarBrand entity = new CarBrand();
        entity.setId(dto.getId());
        entity.setBrand(dto.getBrand());

        return entity;
    }

}
