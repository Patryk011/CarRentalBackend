package org.example.carrent.repository;

import org.example.carrent.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query(value = """
            SELECT car.*
            FROM carrental.car
            WHERE car.id NOT IN (
                SELECT r.car_id
                FROM carrental.rental r
                WHERE (r.start_date <= :startDate AND r.finish_date >= :endDate)
                   OR (r.start_date <= :startDate AND r.finish_date >= :startDate)
                   OR (r.start_date <= :endDate AND r.finish_date >= :endDate)
                   OR (r.start_date >= :startDate AND r.finish_date <= :endDate)
                   AND r.status = 'CONFIRMED'
            )
            AND car.state = 'AVAILABLE';
            """, nativeQuery = true)
    List<Car> findAvailableCarsInDateRange(@Param("startDate") LocalDate startDate,
                                           @Param("endDate") LocalDate endDate);

    @Query(value = """
            SELECT COUNT(*)
            FROM carrental.car c
            WHERE c.id = :id AND EXISTS (
                SELECT 1
                FROM carrental.rental r
                WHERE r.car_id = c.id
                   AND (r.start_date <= :endDate AND r.finish_date >= :startDate) AND r.status = 'CONFIRMED'
            ) OR (c.id = :id AND c.state = 'BLOCKED');
            """, nativeQuery = true)
    Integer checkOneCarAvailability(@Param("startDate") LocalDate startDate,
                                           @Param("endDate") LocalDate endDate,
                                    @Param("id") Long id);
}