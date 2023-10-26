package com.main.server.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductSizeDTO {
    private int id;
    private String name;
    private double extraCoefficient;
}
