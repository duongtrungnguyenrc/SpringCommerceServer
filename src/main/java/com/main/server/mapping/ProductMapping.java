package com.main.server.mapping;

import com.main.server.models.dto.*;
import com.main.server.models.entities.Product;
import com.main.server.models.entities.ProductRating;
import com.main.server.models.entities.ProductUpdateRecord;
import org.springframework.beans.BeanUtils;

import java.util.*;

public class ProductMapping {
    public static ProductDTO bind(Product product) {
        final ProductDTO productDTO = new ProductDTO();
        BeanUtils.copyProperties(product, productDTO);

        productDTO.setId(product.getId());
        productDTO.setGroup(product.getCategory().getGroup().getName());
        productDTO.setType(product.getCategory().getGroup().getType().toString());
        productDTO.setCategory(product.getCategory().getName());
        productDTO.setName(product.getName());
        productDTO.setRated(calculateAverageStar(product.getProductRatings()));
        productDTO.setDescription(product.getDescription());
        productDTO.setInStock(productDTO.getInStock());
        productDTO.setSalePrice(product.getSalePrice());
        productDTO.setCreatedTime(((ProductUpdateRecord) product.getUpdateRecords().toArray()[0]).getTime());

        productDTO.setTag(product.getTag());

        List<PreserveDTO> preserveMethods = new ArrayList<>();
        product.getPreserveMethods().forEach(item -> {
            preserveMethods.add(new PreserveDTO(item.getId(), item.getDescription()));
        });
        productDTO.setPreserveMethods(preserveMethods);

        List<ProductImageDTO> productImages = new ArrayList<>();
        product.getProductImages().forEach(image -> {
            productImages.add(new ProductImageDTO(image.getId(), image.getName(), image.getSrc()));
        });
        productDTO.setImages(productImages);

        List<ProductSizeDTO> productSizes = new ArrayList<>();
        product.getProductSizes().forEach(size -> {
            productSizes.add(new ProductSizeDTO(size.getId(), size.getName(), size.getExtraCoefficient()));
        });
        productDTO.setSizes(productSizes);

        List<ProductColorDTO> productColors = new ArrayList<>();
        product.getProductColors().forEach(color -> {
            productColors.add(new ProductColorDTO(color.getId(), color.getName(), color.getSrc(), color.getExtraCoefficient()));
        });
        productDTO.setColors(productColors);

        List<ProductUpdateRecordDTO> updateRecords = new ArrayList<>();
        product.getUpdateRecords().forEach(record -> {
            updateRecords.add(new ProductUpdateRecordDTO(record.getId(), (Date) record.getTime(), record.getDescription()));
        });

        productDTO.setUpdateRecords(updateRecords);

        if(product.getModel() != null) {
            productDTO.setModel(new ProductModelDTO(product.getModel().getId(), product.getModel().getHeight(), product.getModel().getWeight(), product.getModel().getThreeRoundMeasurements()));
        }
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
