package org.example.carrent.mapper;

import org.example.carrent.dto.DiscountDTO;
import org.example.carrent.entity.Discount;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DiscountMapper {

    public static DiscountDTO toDto(Discount discount) {
        DiscountDTO dto = new DiscountDTO();
        dto.setId(discount.getId());
        dto.setName(discount.getName());
        dto.setPercent(discount.getPercent());
        dto.setExpiryDate(discount.getExpiryDate());
        return dto;
    }

    public Discount toEntity(DiscountDTO dto) {
        Discount entity = new Discount();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setPercent(dto.getPercent());
        entity.setExpiryDate(dto.getExpiryDate());
        return entity;
    }

    public static List<DiscountDTO> toDto(List<Discount> discounts){
        return discounts.stream().map(DiscountMapper::toDto).
                collect(Collectors.toList());
    }

}
