package com.main.server.mapping;

import com.main.server.models.dto.ProductColorDTO;
import com.main.server.models.entities.ProductColor;

public class ProductColorMapping {
    public static ProductColorDTO bind(ProductColor colorEntity) {
        ProductColorDTO color = new ProductColorDTO();

        color.setId(colorEntity.getId());
        color.setName(colorEntity.getName());
        color.setSrc(colorEntity.getSrc());
        color.setExtraCoefficient(colorEntity.getExtraCoefficient());
        return color;
    }
}
