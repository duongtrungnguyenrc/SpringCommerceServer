package com.main.server.services;

import com.main.server.mapping.ProductModelMapping;
import com.main.server.repositories.ProductModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductModelService {
    @Autowired
    ProductModelRepository productModelRepository;

    public Object gelAll() {
        return productModelRepository.findAll().stream().map(ProductModelMapping::bind).toList();
    }
}
