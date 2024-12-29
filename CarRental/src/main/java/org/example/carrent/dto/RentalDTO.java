package org.example.carrent.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.carrent.entity.Car;
import org.example.carrent.entity.Customer;
import org.example.carrent.entity.Discount;
import org.example.carrent.enums.RentalStatus;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RentalDTO {

    private Long id;
    private Long customerId;
    private Long carId;
    private Long discountId;
    private LocalDate startDate;
    private LocalDate finishDate;
    private RentalStatus status;
}
