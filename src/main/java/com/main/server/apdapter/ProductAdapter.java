package com.main.server.apdapter;

import com.main.server.model.dto.*;
import com.main.server.model.entities.Product;
import com.main.server.model.entities.ProductRating;
import com.main.server.model.entities.ProductSize;
import org.springframework.beans.BeanUtils;

import java.util.*;

public class ProductAdapter {
    public static ProductDTO bind(Product product) {
        final ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(product, productDTO);

        productDTO.setProductId(product.getId());
        productDTO.setType(product.getGroup().getType().getName());
        productDTO.setProductGroup(product.getGroup().getName());
        productDTO.setProductName(product.getName());
        productDTO.setRated(calculateAverageStar(product.getProductRatings()));
        productDTO.setDescription(product.getDescription());
        productDTO.setInStock(10);
        productDTO.setTag(product.getTag());
        productDTO.setSalePrice(product.getSalePrice());

        List<PreserveDTO> preserveDTOS = new ArrayList<>();
        product.getPreserveMethods().forEach(item -> {
            preserveDTOS.add(new PreserveDTO(item.getId(), item.getDescription()));
        });
        productDTO.setPreserveMethods(preserveDTOS);

        List<ProductImageDTO> productImageDTOS = new ArrayList<>();
        product.getProductImages().forEach(item -> {
            productImageDTOS.add(new ProductImageDTO(item.getId(), item.getName(), item.getSrc()));
        });
        productDTO.setImages(productImageDTOS);

        List<ProductSizeDTO> productSizeDTOs = new ArrayList<>();
        product.getProductSizes().forEach(item -> {
            productSizeDTOs.add(new ProductSizeDTO(item.getId(), item.getName(), item.getExtraCoefficient()));
        });
        productDTO.setSizeOptions(productSizeDTOs);

        List<ProductColorDTO> productColorDTOS = new ArrayList<>();
        product.getProductColors().forEach(item -> {
            productColorDTOS.add(new ProductColorDTO(item.getId(), item.getName(), item.getSrc(), item.getExtraCoefficient()));
        });
        productDTO.setColorOptions(productColorDTOS);

        return productDTO;
    }

    private static double calculateAverageStar(Collection<ProductRating> ratings) {
        double totalStar = 0;
        for (ProductRating rating : ratings) {
            totalStar += (double) rating.getStar();
        }
        return totalStar / ratings.size();
    }
}
