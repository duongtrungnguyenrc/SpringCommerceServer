package com.main.server.repository;

import com.main.server.model.entities.ProductColor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductColorRepository extends JpaRepository<ProductColor, Integer> {
}
