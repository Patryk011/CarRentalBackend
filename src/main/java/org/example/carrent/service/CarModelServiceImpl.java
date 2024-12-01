package org.example.carrent.service;

import org.example.carrent.dto.CarModelDTO;
import org.example.carrent.entity.CarModel;
import org.example.carrent.mapper.CarModelMapper;
import org.example.carrent.repository.CarBrandRepository;
import org.example.carrent.repository.CarModelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarModelServiceImpl implements CarModelService {
    private final CarModelRepository carModelRepository;
    private final CarModelMapper carModelMapper;
    private final CarBrandRepository carBrandRepository;

    public CarModelServiceImpl(CarModelRepository carModelRepository, CarModelMapper carModelMapper, CarBrandRepository carBrandRepository) {
        this.carModelRepository = carModelRepository;
        this.carModelMapper = carModelMapper;
        this.carBrandRepository = carBrandRepository;
    }

    @Override
    public CarModelDTO addCarModel(CarModelDTO carModelDTO) {
        CarModel carModel = carModelMapper.toEntity(carModelDTO);
        return carModelMapper.toDto(carModelRepository.save(carModel));
    }

    @Override
    public CarModelDTO getCarModelById(Long id) {
        return null;
    }

    @Override
    public List<CarModelDTO> getAllCarModels() {
        return List.of();
    }
}
