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
                WHERE (r.start_date < :startDate AND r.finish_date > :startDate)
                   OR (r.start_date < :endDate AND r.finish_date > :endDate)
            )
            AND car.state = 'AVAILABLE';
            """, nativeQuery = true)
    List<Car> findAvailableCarsInDateRange(@Param("startDate") LocalDate startDate,
                                           @Param("endDate") LocalDate endDate);
}