package org.example.carrent.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.carrent.enums.CarState;
import org.example.carrent.enums.RentalStatus;

import java.time.LocalDate;

@Entity
@Table(name = "rental")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name = "car_id", nullable = false)
    private Car car;

    @ManyToOne
    @JoinColumn(name = "discount_id")
    private Discount discount;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "finish_date")
    private LocalDate finishDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private RentalStatus status;
}
