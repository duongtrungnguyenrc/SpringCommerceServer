package com.main.server.repositories;

import com.main.server.models.entities.ProductSize;
import com.main.server.models.enumerations.EType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSize, Integer> {
    @Query("FROM ProductSize pz WHERE pz.product.category.name = :category AND pz.product.category.group.type = :group GROUP BY pz.name")
    public List<ProductSize> findSizesByCategory(@Param("category") String category, @Param("group") EType group);

    @Query("FROM ProductSize pz WHERE pz.product.category.id = 1 GROUP BY pz.name")
    public List<ProductSize> findSizesByCategory();
}
