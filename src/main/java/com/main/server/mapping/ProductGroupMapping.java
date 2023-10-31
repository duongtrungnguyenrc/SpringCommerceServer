package com.main.server.mapping;

import com.main.server.model.dto.ProductCategoryDTO;
import com.main.server.model.dto.ProductGroupDTO;
import com.main.server.model.entities.ProductGroup;

import java.util.ArrayList;
import java.util.List;

public class ProductGroupMapping {
    public static ProductGroupDTO bind(ProductGroup productGroupEntity) {
        ProductGroupDTO productGroup = new ProductGroupDTO();

        productGroup.setId(productGroupEntity.getId());
        productGroup.setName(productGroupEntity.getName());
        List<ProductCategoryDTO> productCategories = new ArrayList<>();
        productGroupEntity.getCategories().forEach(category -> {
            ProductCategoryDTO productCategory = new ProductCategoryDTO();
            productCategory.setName(category.getName());
            productCategories.add(productCategory);
        });
        productGroup.setProductCategories(productCategories);
        return  productGroup;
    }
}
