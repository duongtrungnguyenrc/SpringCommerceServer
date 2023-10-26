package com.main.server.repository;

import com.main.server.model.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
//    @Query("SELECT * FROM product, product_color " +
//            "WHERE product.id = product_color.product_id and product_color.name like :color " +
//            "GROUP BY product_color.name")
//    List<Product> findProductsByProductColors(@Param("color") String productColor);
}
