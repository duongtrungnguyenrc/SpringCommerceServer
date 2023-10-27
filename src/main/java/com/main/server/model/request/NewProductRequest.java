package com.main.server.model.request;


import com.main.server.model.entities.*;
import com.main.server.model.enumerations.ETag;
import lombok.Data;

import java.util.Collection;
@Data
public class NewProductRequest {
    private String name;
    private String description;
    private ETag tag;
    private double basePrice;
    private double salePrice;
    private int inStock;
    private Collection<ProductSize> sizes;
    private Collection<ProductPreserveMethod> preserveMethods;
    private Collection<ProductImage> images;
    private Collection<ProductColor> colors;
    private Collection<ProductRating> ratings;
    private String updateDescription;
    private int group;
    private int model;
}
