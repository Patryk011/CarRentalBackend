package org.example.carrent.dto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.carrent.enums.CarFuelType;
import org.example.carrent.enums.CarState;
import org.example.carrent.enums.CarTransmission;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarDTO {

    private Long id;
    private Long carModelId;
    private String registrationNumber;
    private LocalDate purchaseDate;
    private CarState state;
    private String vin;
    private Integer productionYear;
    private String color;
    private Integer pricePerHour;
    private CarTransmission transmission;
    private CarFuelType fuelType;
    private Integer seats;
    private LocalDate lastServiceDate;
    private LocalDate nextServiceDate;
    private Integer engineCapacity;
}