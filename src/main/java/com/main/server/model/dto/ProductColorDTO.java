package com.main.server.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductColorDTO {
    private int id;
    private String name;
    private  String src;
    private double extraCoefficient;
}
