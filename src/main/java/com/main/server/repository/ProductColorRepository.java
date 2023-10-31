package com.main.server.repository;

import com.main.server.model.entities.ProductColor;
import com.main.server.model.entities.ProductSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductColorRepository extends JpaRepository<ProductColor, Integer> {
    @Query("FROM ProductColor pc WHERE pc.product.category.name = :category GROUP BY pc.name")
    public List<ProductColor> findColorOptions(@Param("category") String category);
}
