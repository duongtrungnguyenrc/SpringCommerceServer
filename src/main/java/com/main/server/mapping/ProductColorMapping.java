package com.main.server.mapping;

import com.main.server.model.dto.ProductColorDTO;
import com.main.server.model.entities.ProductColor;

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
