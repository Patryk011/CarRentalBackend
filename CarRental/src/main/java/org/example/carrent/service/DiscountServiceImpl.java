package org.example.carrent.service;

import org.example.carrent.dto.DiscountDTO;
import org.example.carrent.entity.Discount;
import org.example.carrent.exception.ResourceNotFoundException;
import org.example.carrent.mapper.DiscountMapper;
import org.example.carrent.repository.DiscountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiscountServiceImpl implements DiscountService {
    private final DiscountRepository discountRepository;
    private final DiscountMapper discountMapper;

    public DiscountServiceImpl(DiscountRepository discountRepository, DiscountMapper discountMapper) {
        this.discountRepository = discountRepository;
        this.discountMapper = discountMapper;
    }

    @Override
    public List<DiscountDTO> getAllDiscounts() {
        return discountMapper.toDto(discountRepository.findAll());
    }

    @Override
    public DiscountDTO findDiscountById(Long id) {
        Discount discount = discountRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Discount with id " + id + " not found"));
        return discountMapper.toDto(discount);
    }

    @Override
    public DiscountDTO addDiscount(DiscountDTO discountDTO) {
        Discount discount = discountMapper.toEntity(discountDTO);
        return discountMapper.toDto(discountRepository.save(discount));
    }
}
