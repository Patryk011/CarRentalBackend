package org.example.carrent.Controllers;

import org.example.carrent.dto.CarDTO;
import org.example.carrent.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/Cars")
public class CarController {
    private final CarService carService;

    @Autowired
    public CarController(CarService carService) {
        this.carService = carService;
    }

    @PostMapping
    public CarDTO addCar(@RequestBody CarDTO carDTO) {
        return carService.addCar(carDTO);
    }

    @GetMapping("/all")
    public List<CarDTO> getAllCars() {
        return carService.getAllCars();
    }

    @GetMapping("/{id}")
    public CarDTO getCarById(@PathVariable Long id) {
        return carService.findCarById(id);
    }
}
