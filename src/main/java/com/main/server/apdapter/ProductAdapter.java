package com.main.server.apdapter;

import com.main.server.model.dto.*;
import com.main.server.model.entities.Product;
import com.main.server.model.entities.ProductRating;
import com.main.server.model.response.ProductDetailResponse;
import org.springframework.beans.BeanUtils;

import java.util.*;

public class ProductAdapter {
    public static ProductDetailResponse bind(Product product) {
        final ProductDetailResponse productDetailResponse = new ProductDetailResponse();
        BeanUtils.copyProperties(product, productDetailResponse);

        productDetailResponse.setProductId(product.getId());
        productDetailResponse.setType(product.getGroup().getType().getName());
        productDetailResponse.setProductGroup(product.getGroup().getName());
        productDetailResponse.setProductName(product.getName());
        productDetailResponse.setRated(calculateAverageStar(product.getProductRatings()));
        productDetailResponse.setDescription(product.getDescription());
        productDetailResponse.setInStock(10);
        productDetailResponse.setTag(product.getTag());
        productDetailResponse.setSalePrice(product.getSalePrice());

        List<PreserveDTO> preserveDTOS = new ArrayList<>();
        product.getPreserveMethods().forEach(item -> {
            preserveDTOS.add(new PreserveDTO(item.getId(), item.getDescription()));
        });
        productDetailResponse.setPreserveMethods(preserveDTOS);

        List<ProductImageDTO> productImageDTOS = new ArrayList<>();
        product.getProductImages().forEach(item -> {
            productImageDTOS.add(new ProductImageDTO(item.getId(), item.getName(), item.getSrc()));
        });
        productDetailResponse.setImages(productImageDTOS);

        List<ProductSizeDTO> productSizeDTOs = new ArrayList<>();
        product.getProductSizes().forEach(item -> {
            productSizeDTOs.add(new ProductSizeDTO(item.getId(), item.getName(), item.getExtraCoefficient()));
        });
        productDetailResponse.setSizeOptions(productSizeDTOs);

        List<ProductColorDTO> productColorDTOS = new ArrayList<>();
        product.getProductColors().forEach(item -> {
            productColorDTOS.add(new ProductColorDTO(item.getId(), item.getName(), item.getSrc(), item.getExtraCoefficient()));
        });
        productDetailResponse.setColorOptions(productColorDTOS);

        return productDetailResponse;
    }

    private static double calculateAverageStar(Collection<ProductRating> ratings) {
        if (ratings.isEmpty()) {
            return 0.0;
        }
        double totalStar = 0.0;
        for (ProductRating rating : ratings) {
            totalStar += (double) rating.getStar();
        }
        return (Double) (totalStar / ratings.size());
    }

}
