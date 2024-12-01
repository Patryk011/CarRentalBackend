package org.example.carrent.Controllers;

import org.example.carrent.dto.CarModelDTO;
import org.example.carrent.service.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/carModels")
public class CarModelController {
    private final CarModelService carModelService;

    @Autowired
    public CarModelController(CarModelService carModelService) {
        this.carModelService = carModelService;
    }

    @PostMapping
    public CarModelDTO addCarModel(@RequestBody CarModelDTO carModelDTO) {
        return carModelService.addCarModel(carModelDTO);
    }
}
