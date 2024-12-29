package org.example.carrent.service;

import org.example.carrent.dto.DiscountDTO;

import java.util.List;

public interface DiscountService {
    List<DiscountDTO> getAllDiscounts();
    DiscountDTO findDiscountById(Long id);
    DiscountDTO addDiscount(DiscountDTO discount);
}
