package com.main.server.mapping;

import com.main.server.models.dto.ProductCategoryDTO;
import com.main.server.models.dto.ProductGroupDTO;
import com.main.server.models.entities.ProductGroup;

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
            productCategory.setId(category.getId());
            productCategory.setName(category.getName());
            productCategories.add(productCategory);
        });
        productGroup.setProductCategories(productCategories);
        return  productGroup;
    }
}
