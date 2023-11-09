package com.main.server.repositories;

import com.main.server.models.entities.ProductGroup;
import com.main.server.models.enumerations.EType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductGroupRepository extends JpaRepository<ProductGroup, Integer> {
    public List<ProductGroup> findProductGroupByType(EType type);
}
