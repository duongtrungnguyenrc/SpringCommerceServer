package com.main.server.mapping;

import com.main.server.models.dto.ProductModelDTO;
import com.main.server.models.entities.ProductModel;

public class ProductModelMapping {
    public static ProductModelDTO bind(ProductModel modelEntity) {
        ProductModelDTO model = new ProductModelDTO();

        model.setId(modelEntity.getId());
        model.setHeight(modelEntity.getHeight());
        model.setWeight(modelEntity.getWeight());
        model.setThreeRoundMeasurements(modelEntity.getThreeRoundMeasurements());
        return model;
    }
}
