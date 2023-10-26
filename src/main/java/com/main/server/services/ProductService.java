package com.main.server.services;

import com.main.server.apdapter.ProductAdapter;
import com.main.server.model.dto.ProductDTO;
import com.main.server.model.entities.Product;
import com.main.server.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    ProductTypeRepository productTypeRepository;

    @Autowired
    ProductGroupRepository productGroupRepository;

    @Autowired
    ProductModelRepository productModelRepository;

    @Autowired
    UpdateRecordRepository updateRecordRepository;

    @Autowired
    ProductSizeRepository productSizeRepository;

    @Autowired
    PreserveMethodRepository preserveMethodRepository;

    @Autowired
    ProductImageRepository productImageRepository;

    @Autowired
    ProductColorRepository productColorRepository;

    @Autowired
    ProductRatingRepository productRatingRepository;


    public Map<String, Object> getALlProducts(int page, int limit) {
        Map<String, Object> data = new HashMap<>();
        data.put("products", productRepository.findAll(PageRequest.of(page - 1, limit)).stream().map(ProductAdapter::bind));
        data.put("page", page);
        data.put("totalPages", Math.round((float) productRepository.count() / limit));
        return data;
    }

//    public Map<String, Object> getProductsByColor(String color) {
//        Map<String, Object> data = new HashMap<>();
//        data.put("products", productRepository.findProductsByProductColors(color).stream().map(ProductAdapter::bind));
//        return data;
//    }

    public ProductDTO getProductById(int id) {
        Product product = productRepository.findById(id).orElse(null);
        return ProductAdapter.bind(product);
    }
}
