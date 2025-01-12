package org.example.carrent.service;

import org.example.carrent.dto.CarModelDTO;
import org.example.carrent.entity.CarModel;
import org.example.carrent.exception.ResourceNotFoundException;
import org.example.carrent.mapper.CarModelMapper;
import org.example.carrent.repository.CarModelRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarModelServiceImpl implements CarModelService {
    private final CarModelRepository carModelRepository;
    private final CarModelMapper carModelMapper;

    public CarModelServiceImpl(CarModelRepository carModelRepository, CarModelMapper carModelMapper) {
        this.carModelRepository = carModelRepository;
        this.carModelMapper = carModelMapper;
    }

    @Override
    public CarModelDTO addCarModel(CarModelDTO carModelDTO) {
        CarModel carModel = carModelMapper.toEntity(carModelDTO);
        return carModelMapper.toDto(carModelRepository.save(carModel));
    }

    @Override
    public CarModelDTO findCarModelById(Long id) {
        CarModel carModel = carModelRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Car model with id " + id + " not found"));
        return carModelMapper.toDto(carModel);
    }

    @Override
    public List<CarModelDTO> findAllWithBrandId(Long id) {
        return carModelMapper.toDto(carModelRepository.findAllWithBrandId(id));
    }

    @Override
    public List<CarModelDTO> findAllWithBrandName(String brandName) {
        return carModelMapper.toDto(carModelRepository.findAllWithBrandName(brandName));
    }

    @Override
    public List<CarModelDTO> getAllCarModels() {
        return carModelMapper.toDto(carModelRepository.findAll());
    }
}
