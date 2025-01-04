package org.example.carrent.Controllers;

import org.example.carrent.dto.CarDTO;
import org.example.carrent.dto.RentalDTO;
import org.example.carrent.service.CarService;
import org.example.carrent.service.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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



    @GetMapping("/all")
    public List<RentalDTO> getAllRentals() {
        return rentalService.getAllRentals();
    }



    @PatchMapping("/{id}/cancel")
    public RentalDTO cancelRental(@PathVariable Long id) {
        return rentalService.cancelRental(id);
    }

    @PostMapping
    public ResponseEntity<RentalDTO> createRental(@RequestBody RentalDTO rentalDTO) {
        RentalDTO savedRental = rentalService.addRental(rentalDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedRental);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalDTO> getRentalById(@PathVariable Long id) {
        RentalDTO rentalDTO = rentalService.findRentalById(id);
        return ResponseEntity.ok(rentalDTO);
    }
}
