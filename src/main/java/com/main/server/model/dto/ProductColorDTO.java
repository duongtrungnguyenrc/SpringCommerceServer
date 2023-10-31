package com.main.server.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductColorDTO {
    private int id;
    private String name;
    private  String src;
    private double extraCoefficient;
}
