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

    private Long Id;
    private String registrationNumber;
    private LocalDate purchaseDate;
    private CarState state;
    private String vin;
    private LocalDate productionYear;
    private String color;
    private Integer pricePerHour;
    private CarTransmission transmission;
    private CarFuelType fuelType;
    private Integer seats;
    private LocalDate lastServiceDate;
    private LocalDate nextServiceDate;
    private LocalDate engineCapacity;

}
