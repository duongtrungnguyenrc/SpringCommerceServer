package com.main.server.mapping;

import com.main.server.models.dto.ProductColorDTO;
import com.main.server.models.dto.ProductImageDTO;
import com.main.server.models.entities.ProductColor;
import com.main.server.models.entities.ProductImage;

public class ProductImageMapping {
    public static ProductImageDTO bind(ProductImage imageEntity) {
        ProductImageDTO image = new ProductImageDTO();

        image.setId(imageEntity.getId());
        image.setName(imageEntity.getName());
        image.setSrc(imageEntity.getSrc());
        return image;
    }
}
