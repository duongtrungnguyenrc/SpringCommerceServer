package com.main.server.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductColorDTO {
    private Integer id;
    private String name;
    private  String src;
    private double extraCoefficient;
}
