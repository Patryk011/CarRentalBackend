package org.example.carrent.Controllers;

import org.example.carrent.dto.CarDTO;
import org.example.carrent.dto.RentalDTO;
import org.example.carrent.service.CarService;
import org.example.carrent.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rentals")
public class RentalController {
    private final RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService) {
        this.rentalService = rentalService;
    }

    @PostMapping
    public RentalDTO addRental(@RequestBody RentalDTO rentalDTO) {
        return rentalService.addRental(rentalDTO);
    }

    @GetMapping("/all")
    public List<RentalDTO> getAllRentals() {
        return rentalService.getAllRentals();
    }

    @GetMapping("/{id}")
    public RentalDTO getRentalById(@PathVariable Long id) {
        return rentalService.findRentalById(id);
    }
}
