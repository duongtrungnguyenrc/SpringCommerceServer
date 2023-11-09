package com.main.server.models.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductGroupDTO {
    private int id;
    private String name;
    private List<ProductCategoryDTO> productCategories;
}
