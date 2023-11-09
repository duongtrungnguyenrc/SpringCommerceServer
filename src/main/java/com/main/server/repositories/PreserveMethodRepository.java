package com.main.server.repositories;

import com.main.server.models.entities.ProductPreserveMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreserveMethodRepository extends JpaRepository<ProductPreserveMethod, Integer> {
}
