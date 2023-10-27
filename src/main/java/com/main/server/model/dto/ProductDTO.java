package com.main.server.model.dto;

import com.main.server.model.entities.*;
import com.main.server.model.enumerations.ETag;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private int id;
    private String name;
    private String description;
    private ETag tag;
    private double basePrice;
    private double salePrice;
    private int inStock;
    private Collection<ProductUpdateRecord> updateRecords;
    private Collection<ProductSizeDTO> productSizes;
    private Collection<PreserveDTO> preserveMethods;
    private Collection<ProductImageDTO> productImages;
    private Collection<ProductColorDTO> productColors;
    private Collection<ProductRatingDTO> productRatings;
    private ProductGroup group;
    private ProductModel model;
}
