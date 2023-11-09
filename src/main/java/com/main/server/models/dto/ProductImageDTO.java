package com.main.server.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductImageDTO {
    private Integer id;
    private String name;
    private  String src;
}
