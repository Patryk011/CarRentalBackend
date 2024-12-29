package org.example.carrent.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarModelDTO {

    private Long id;
    private String model;
    private Long carBrandId;
    private String carBrandName;
}
