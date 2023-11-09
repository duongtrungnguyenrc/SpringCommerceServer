package com.main.server.repositories;

import com.main.server.models.entities.ProductColor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductColorRepository extends JpaRepository<ProductColor, Integer> {
    @Query("FROM ProductColor pc WHERE pc.product.category.name = :category GROUP BY pc.name")
    public List<ProductColor> findColorOptions(@Param("category") String category);
}
