package org.example.carrent.Controllers;

import org.example.carrent.dto.CarDTO;
import org.example.carrent.dto.RentalDTO;
import org.example.carrent.entity.Car;
import org.example.carrent.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @GetMapping("/available")
    public List<CarDTO> getAvailableCars(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate formattedStartDate = LocalDate.parse(startDate, formatter);
        LocalDate formattedEndDate = LocalDate.parse(endDate, formatter);
        return carService.findAvailableCars(formattedStartDate,formattedEndDate);
    }

    @GetMapping("/available/{id}")
    public Integer checkOneCarAvailability(@RequestParam("startDate") String startDate, @RequestParam("endDate") String endDate,@PathVariable Long id) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate formattedStartDate = LocalDate.parse(startDate, formatter);
        LocalDate formattedEndDate = LocalDate.parse(endDate, formatter);
        return carService.checkOneCarAvailability(formattedStartDate,formattedEndDate,id);
    }

    @PatchMapping("/{id}/block")
    public CarDTO blockCar(@PathVariable Long id) {
        return carService.blockCar(id);
    }

    @PatchMapping("/{id}/unlock")
    public CarDTO unlockCar(@PathVariable Long id) {
        return carService.unlockCar(id);
    }
}
