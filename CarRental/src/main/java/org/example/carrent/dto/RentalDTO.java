package org.example.carrent.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.carrent.enums.RentalStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RentalDTO {

    private Long id;
    private Long customerId;
    private Long carId;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate finishDate;
    private RentalStatus status;
    private BigDecimal totalCost;
    private Integer discountPercentage;
    private String carBrand;
    private String carModel;
    private String registrationNumber;
}
