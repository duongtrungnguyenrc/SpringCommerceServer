package com.main.server.model.dto;

import com.main.server.model.entities.Product;
import com.main.server.model.entities.ProductGroup;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.List;

@Data
public class ProductCategoryDTO {
    private String name;
}
