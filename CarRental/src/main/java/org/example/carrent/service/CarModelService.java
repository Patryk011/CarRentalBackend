package org.example.carrent.service;

import org.example.carrent.dto.CarModelDTO;

import java.util.List;

public interface CarModelService {

    CarModelDTO addCarModel(CarModelDTO carModelDTO);

    CarModelDTO findCarModelById(Long id);

    List<CarModelDTO> getAllCarModels();

    List<CarModelDTO> findAllWithBrandId(Long id);

    List<CarModelDTO> findAllWithBrandName(String brandName);
}
