package com.main.server.repository;

import com.main.server.model.entities.ProductPreserveMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreserveMethodRepository extends JpaRepository<ProductPreserveMethod, Integer> {
}
