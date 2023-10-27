package com.main.server.services;

import com.main.server.apdapter.ProductAdapter;

import com.main.server.model.entities.*;
import com.main.server.model.request.NewProductRequest;
import com.main.server.model.response.ProductDetailResponse;
import com.main.server.repository.*;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    ProductGroupRepository productGroupRepository;

    @Autowired
    ProductModelRepository productModelRepository;
    public Map<String, Object> getALlProducts(int page, int limit) {
        Map<String, Object> data = new HashMap<>();
        data.put("products", productRepository.findAll(PageRequest.of(page - 1, limit)).stream().map(ProductAdapter::bind));
        data.put("page", page);
        data.put("totalPages", Math.round((float) productRepository.count() / limit));
        return data;
    }

    public Map<String, Object> getProductsByColor(@Nullable String color) {
        Map<String, Object> data = new HashMap<>();
        data.put("products", productRepository.findProductsByProductColors(color).stream().map(ProductAdapter::bind));
        return data;
    }

    public Map<String, Object> getProductsBySize(@Nullable String size) {
        Map<String, Object> data = new HashMap<>();
        data.put("products", productRepository.findProductsByProductSizes(size).stream().map(ProductAdapter::bind));
        return data;
    }

    public ProductDetailResponse getProductById(int id) {
        Product product = productRepository.findById(id).orElse(null);
        return ProductAdapter.bind(product);
    }

    public void addProduct(NewProductRequest newProduct) {
        try {
            Product product = new Product();
            product.setName(newProduct.getName());
            product.setDescription(newProduct.getDescription());
            product.setBasePrice(newProduct.getBasePrice());
            product.setSalePrice(newProduct.getSalePrice());
            product.setTag(newProduct.getTag());

            List<ProductSize> productSizes = new ArrayList<>();
            for (ProductSize size : newProduct.getSizes()) {
                ProductSize productSize = new ProductSize();
                productSize.setName(size.getName());
                productSize.setExtraCoefficient(size.getExtraCoefficient());
                productSize.setProduct(product); // Set the relationship
                productSizes.add(productSize);
            }
            List<ProductColor> productColors = new ArrayList<>();
            for (ProductColor color : newProduct.getColors()) {
                ProductColor productColor = new ProductColor();
                productColor.setName(color.getName());
                productColor.setSrc(color.getSrc());
                productColor.setExtraCoefficient(color.getExtraCoefficient());
                productColor.setProduct(product); // Set the relationship
                productColors.add(productColor);
            }
            List<ProductImage> productImages = new ArrayList<>();
            for(ProductImage image : newProduct.getImages()) {
                ProductImage productImage = new ProductImage();
                productImage.setName(image.getName());
                productImage.setSrc(image.getSrc());
                productImage.setProduct(product);
                productImages.add(productImage);
            }
            product.setProductSizes(productSizes);
            product.setProductColors(productColors);
            product.setProductImages(productImages);

            product.setGroup(productGroupRepository.findById(newProduct.getGroup()).orElse(null));
            product.setModel(productModelRepository.findById(newProduct.getGroup()).orElse(null));

            List<ProductUpdateRecord> productUpdateRecords = new ArrayList<>();
            productUpdateRecords.add(
                    new ProductUpdateRecord(
                            new Date(),
                            newProduct.getUpdateDescription(),
                            product
                    )
            );
            product.setUpdateRecords(productUpdateRecords);
            productRepository.save(product);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
