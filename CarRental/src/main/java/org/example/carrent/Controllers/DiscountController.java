package org.example.carrent.Controllers;

import org.example.carrent.dto.CarBrandDTO;
import org.example.carrent.dto.DiscountDTO;
import org.example.carrent.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/discounts")
public class DiscountController {
    private final DiscountService discountService;

    @Autowired
    public DiscountController(DiscountService discountService) {
        this.discountService = discountService;
    }

    @GetMapping("/all")
    public List<DiscountDTO> getAllDiscounts() {
        return discountService.getAllDiscounts();
    }

    @GetMapping("/{id}")
    public DiscountDTO getDiscountById(@PathVariable Long id) {
        return discountService.findDiscountById(id);
    }

    @PostMapping
    public DiscountDTO addDiscount(@RequestBody DiscountDTO discountDTO) {
        return discountService.addDiscount(discountDTO);
    }
}