package com.main.server.repositories;

import com.main.server.models.entities.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Integer> {
    @Query("FROM ProductImage i ORDER BY RAND() LIMIT 5")
    List<ProductImage> findRandom();
}
