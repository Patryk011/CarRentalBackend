package org.example.carrent.repository;

import org.example.carrent.dto.CarModelDTO;
import org.example.carrent.entity.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CarModelRepository extends JpaRepository<CarModel, Long> {

    @Query(value = """
            SELECT * FROM carrental.car_model cm WHERE cm.brand_id = :brandId
            """, nativeQuery = true)
    List<CarModel> findAllWithBrandId(@Param("brandId") Long brandId);


    @Query(value = """
            SELECT cm.* FROM carrental.car_model cm JOIN carrental.car_brand cb ON cm.brand_id = cb.id WHERE cb.brand = :brandName
            """, nativeQuery = true)
    List<CarModel> findAllWithBrandName(@Param("brandName") String brandName);


}

