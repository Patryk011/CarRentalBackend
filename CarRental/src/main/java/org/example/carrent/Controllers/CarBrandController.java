package org.example.carrent.Controllers;

import org.example.carrent.dto.CarBrandDTO;
import org.example.carrent.service.CarBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carBrands")
public class CarBrandController {
    private final CarBrandService carBrandService;

    @Autowired
    public CarBrandController(CarBrandService carBrandService) {
        this.carBrandService = carBrandService;
    }

    @GetMapping("/all")
    public List<CarBrandDTO> getAllCarBrands(){
        return carBrandService.getAllCarBrands();
    }

    @GetMapping("/{id}")
    public CarBrandDTO getCarBrandById(@PathVariable Long id){
        return carBrandService.findCarBrandById(id);
    }

    @PostMapping
    public CarBrandDTO addCarBrand(@RequestBody CarBrandDTO carBrandDTO){
        return carBrandService.addCarBrand(carBrandDTO);
    }
}
