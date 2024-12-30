package org.example.carrent.service;

import org.example.carrent.dto.CarBrandDTO;
import org.example.carrent.entity.CarBrand;
import org.example.carrent.exception.ResourceNotFoundException;
import org.example.carrent.mapper.CarBrandMapper;
import org.example.carrent.mapper.CustomerMapper;
import org.example.carrent.repository.CarBrandRepository;
import org.example.carrent.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarBrandServiceImpl implements CarBrandService {
    private final CarBrandRepository carBrandRepository;
    private final CarBrandMapper carBrandMapper;

    public CarBrandServiceImpl(CarBrandRepository carBrandRepository, CarBrandMapper carBrandMapper) {
        this.carBrandRepository = carBrandRepository;
        this.carBrandMapper = carBrandMapper;
    }

    @Override
    public List<CarBrandDTO> getAllCarBrands() {
        return  carBrandMapper.toDto(carBrandRepository.findAll());
    }

    @Override
    public CarBrandDTO addCarBrand(CarBrandDTO carBrandDTO) {
        if (carBrandDTO.getId() != null) {
            throw new IllegalArgumentException("New car brands should not have an ID");
        }
        CarBrand carBrand = carBrandMapper.toEntity(carBrandDTO);
        return carBrandMapper.toDto(carBrandRepository.save(carBrand));
    }

    @Override
    public CarBrandDTO findCarBrandById(Long id) {
        CarBrand carBrand = carBrandRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Car brand with id " + id + " not found"));
        return carBrandMapper.toDto(carBrand);
    }
}
