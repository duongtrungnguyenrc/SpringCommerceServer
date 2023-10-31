package com.main.server.mapping;

import com.main.server.model.dto.ProductSizeDTO;
import com.main.server.model.entities.ProductSize;

public class ProductSizeMapping {
    public static ProductSizeDTO bind(ProductSize sizeEntity) {
       ProductSizeDTO size = new ProductSizeDTO();
       size.setId((sizeEntity.getId()));
       size.setName(sizeEntity.getName());
       size.setExtraCoefficient(sizeEntity.getExtraCoefficient());
        return size;
    }
}
