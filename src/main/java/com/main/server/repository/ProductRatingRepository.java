package com.main.server.repository;

import com.main.server.model.entities.ProductRating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRatingRepository extends JpaRepository<ProductRating, Integer> {
}
