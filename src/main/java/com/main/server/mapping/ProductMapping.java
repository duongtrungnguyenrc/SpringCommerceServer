package com.main.server.mapping;

import com.main.server.model.dto.*;
import com.main.server.model.entities.Product;
import com.main.server.model.entities.ProductRating;
import org.springframework.beans.BeanUtils;

import java.util.*;

public class ProductMapping {
    public static ProductDTO bind(Product product) {
        final ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(product, productDTO);

        productDTO.setProductId(product.getId());
        productDTO.setType(product.getCategory().getGroup().getType().toString());
        productDTO.setProductGroup(product.getCategory().getGroup().getName());
        productDTO.setProductName(product.getName());
        productDTO.setRated(calculateAverageStar(product.getProductRatings()));
        productDTO.setDescription(product.getDescription());
        productDTO.setInStock(10);
        productDTO.setTag(product.getTag());
        productDTO.setSalePrice(product.getSalePrice());

        List<PreserveDTO> preserveMethods = new ArrayList<>();
        product.getPreserveMethods().forEach(item -> {
            preserveMethods.add(new PreserveDTO(item.getId(), item.getDescription()));
        });
        productDTO.setPreserveMethods(preserveMethods);

        List<ProductImageDTO> productImages = new ArrayList<>();
        product.getProductImages().forEach(item -> {
            productImages.add(new ProductImageDTO(item.getId(), item.getName(), item.getSrc()));
        });
        productDTO.setImages(productImages);

        List<ProductSizeDTO> productSizes = new ArrayList<>();
        product.getProductSizes().forEach(item -> {
            productSizes.add(new ProductSizeDTO(item.getId(), item.getName(), item.getExtraCoefficient()));
        });
        productDTO.setSizeOptions(productSizes);

        List<ProductColorDTO> productColors = new ArrayList<>();
        product.getProductColors().forEach(item -> {
            productColors.add(new ProductColorDTO(item.getId(), item.getName(), item.getSrc(), item.getExtraCoefficient()));
        });
        productDTO.setColorOptions(productColors);

        return productDTO;
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
