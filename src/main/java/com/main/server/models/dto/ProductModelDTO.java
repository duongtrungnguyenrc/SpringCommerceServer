package com.main.server.models.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductModelDTO {
    private int id;
    private String name;
    private String height;
    private String weight;
    private String threeRoundMeasurements;
}
