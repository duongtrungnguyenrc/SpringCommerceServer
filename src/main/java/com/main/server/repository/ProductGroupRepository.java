package com.main.server.repository;

import com.main.server.model.entities.ProductGroup;
import com.main.server.model.enumerations.EType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductGroupRepository extends JpaRepository<ProductGroup, Integer> {
    public List<ProductGroup> findProductGroupByType(EType type);
}
