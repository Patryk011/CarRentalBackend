package org.example.carrent.Controllers;

import org.example.carrent.dto.CarModelDTO;
import org.example.carrent.service.CarModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/all")
    public List<CarModelDTO> getAllCarModels() {
        return carModelService.getAllCarModels();
    }

    @GetMapping("/{id}")
    public CarModelDTO getCarModelById(@PathVariable Long id) {
        return  carModelService.findCarModelById(id);
    }
}
