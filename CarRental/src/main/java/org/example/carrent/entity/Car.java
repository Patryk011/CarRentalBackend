package org.example.carrent.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.carrent.enums.CarFuelType;
import org.example.carrent.enums.CarState;
import org.example.carrent.enums.CarTransmission;

import java.time.LocalDate;

@Entity
@Table(name = "car")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(name = "registration_number", nullable = false,unique = true)
    private String registrationNumber;

    @Column(name = "purchase_date", nullable = false)
    private LocalDate purchaseDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private CarState state;

    @Column(name = "vin", nullable = false)
    private String vin;

    @Column(name = "production_year", nullable = false)
    private LocalDate productionYear;

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "price_per_hour", nullable = false)
    private Integer pricePerHour;

    @Enumerated(EnumType.STRING)
    @Column(name = "transmission", nullable = false)
    private CarTransmission transmission;

    @Enumerated(EnumType.STRING)
    @Column(name = "fuel_type", nullable = false)
    private CarFuelType fuelType;

    @Column(name = "seats", nullable = false)
    private Integer seats;

    @Column(name = "last_service_date")
    private LocalDate lastServiceDate;

    @Column(name = "next_service_date")
    private LocalDate nextServiceDate;

    @Column(name = "engine_capacity")
    private LocalDate engineCapacity;
}
