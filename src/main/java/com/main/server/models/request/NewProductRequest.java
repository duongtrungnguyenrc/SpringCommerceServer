package com.main.server.models.request;


import com.main.server.models.dto.PreserveDTO;
import com.main.server.models.dto.ProductColorDTO;
import com.main.server.models.dto.ProductImageDTO;
import com.main.server.models.dto.ProductSizeDTO;
import com.main.server.models.entities.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class NewProductRequest {
    private String name;
    private String description;
    private String tag;
    private double basePrice;
    private double salePrice;
    private int inStock;
    private Collection<ProductSizeDTO> sizes;
    private Collection<PreserveDTO> preserveMethods;
    private Collection<ProductImageDTO> images;
    private Collection<ProductColorDTO> colors;
    private int category;
    private int model;
}
