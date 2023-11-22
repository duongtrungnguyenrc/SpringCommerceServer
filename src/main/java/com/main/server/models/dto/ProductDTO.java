package com.main.server.models.dto;

import com.main.server.models.entities.ProductUpdateRecord;
import com.main.server.models.enumerations.ETag;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {
    private Integer id;
    private String group;
    private String type;
    private String category;
    private String name;
    private Date createdTime;
    private double rated = 0.0;
    private String description;
    private double basePrice;
    private double salePrice;
    private int inStock;
    private List<PreserveDTO> preserveMethods;
    private ETag tag;
    private List<ProductImageDTO> images;
    private List<ProductSizeDTO> sizes;
    private List<ProductColorDTO> colors;
    private List<ProductUpdateRecordDTO> updateRecords;
    private ProductModelDTO model;
}
