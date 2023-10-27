package com.main.server.repository;

import com.main.server.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query("FROM Product p JOIN p.productColors pc WHERE pc.name LIKE %:color%")
    List<Product> findProductsByProductColors(@Param("color") String productColor);

    @Query("FROM Product p JOIN p.productSizes pc WHERE pc.name LIKE %:size%")
    List<Product> findProductsByProductSizes(@Param("size") String productColor);

}
