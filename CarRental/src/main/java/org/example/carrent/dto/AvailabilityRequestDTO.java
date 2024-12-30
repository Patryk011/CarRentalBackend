package org.example.carrent.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AvailabilityRequestDTO {
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
