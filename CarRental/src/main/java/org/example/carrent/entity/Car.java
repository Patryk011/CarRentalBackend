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
@NoArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "model_id", nullable = false)
    private CarModel carModel;

    @Column(name = "registration_number", nullable = false,unique = true)
    private String registrationNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "state", nullable = false)
    private CarState state;



    @Column(name = "production_year", nullable = false)
    private Integer productionYear;

    @Column(name = "color", nullable = false)
    private String color;

    @Column(name = "price_per_day", nullable = false)
    private Long pricePerDay;

    @Enumerated(EnumType.STRING)
    @Column(name = "transmission", nullable = false)
    private CarTransmission transmission;

    @Enumerated(EnumType.STRING)
    @Column(name = "fuel_type", nullable = false)
    private CarFuelType fuelType;

    @Column(name = "seats", nullable = false)
    private Integer seats;

    @Column(name = "engine_capacity")
    private Integer engineCapacity;
}