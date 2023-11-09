package com.main.server.repositories;

import com.main.server.models.entities.ProductSize;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSize, Integer> {
    @Query("FROM ProductSize pz WHERE pz.product.category.name = :category GROUP BY pz.name")
    public List<ProductSize> findSizeOptions(@Param("category") String category);
}
