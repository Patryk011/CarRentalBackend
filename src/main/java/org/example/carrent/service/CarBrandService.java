package org.example.carrent.service;

import org.example.carrent.dto.CarBrandDTO;

import java.util.List;

public interface CarBrandService {
    List<CarBrandDTO> getAllCarBrands();

    CarBrandDTO addCarBrand(CarBrandDTO carBrandDTO);

    CarBrandDTO findCarBrandById(Long id);

    }
