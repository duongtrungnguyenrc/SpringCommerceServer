package com.main.server.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductImageDTO {
    private int id;
    private String name;
    private  String src;
}
