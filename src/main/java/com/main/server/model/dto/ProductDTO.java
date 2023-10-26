package com.main.server.model.dto;

import com.main.server.model.entities.ProductColor;
import com.main.server.model.entities.ProductImage;
import com.main.server.model.entities.ProductPreserveMethod;
import com.main.server.model.entities.ProductSize;
import com.main.server.model.enumerations.ETag;
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
    private int productId;
    private String type;
    private String productGroup;
    private String productName;
    private Date createdTime;
    private double rated;
    private String description;
    private double basePrice;
    private double salePrice;
    private int inStock;
    private List<PreserveDTO> preserveMethods;
    private ETag tag;
    private List<ProductImageDTO> images;
    private List<ProductSizeDTO> sizeOptions;
    private List<ProductColorDTO> ColorOptions;
}
