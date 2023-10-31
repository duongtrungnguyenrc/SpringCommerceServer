package com.main.server.model.dto;

import com.main.server.model.enumerations.EType;
import lombok.Data;

import java.util.List;

@Data
public class ProductGroupDTO {
    private int id;
    private String name;
    private List<ProductCategoryDTO> productCategories;
}
