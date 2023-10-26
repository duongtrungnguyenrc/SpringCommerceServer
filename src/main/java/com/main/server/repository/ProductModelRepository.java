package com.main.server.repository;

import com.main.server.model.entities.ProductModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductModelRepository extends JpaRepository<ProductModel, Integer> {
}
