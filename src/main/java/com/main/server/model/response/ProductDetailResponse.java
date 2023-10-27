package com.main.server.model.response;

import com.main.server.model.dto.PreserveDTO;
import com.main.server.model.dto.ProductColorDTO;
import com.main.server.model.dto.ProductImageDTO;
import com.main.server.model.dto.ProductSizeDTO;
import com.main.server.model.enumerations.ETag;
import lombok.Data;

import java.util.Date;
import java.util.List;
@Data
public class ProductDetailResponse {
    private int productId;
    private String type;
    private String productGroup;
    private String productName;
    private Date createdTime;
    private double rated = 0.0;
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
