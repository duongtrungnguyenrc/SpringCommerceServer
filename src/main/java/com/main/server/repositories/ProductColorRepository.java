package com.main.server.repositories;

import com.main.server.models.entities.ProductColor;
import com.main.server.models.enumerations.EType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductColorRepository extends JpaRepository<ProductColor, Integer> {
    @Query("FROM ProductColor pc WHERE pc.product.category.name = :category AND pc.product.category.group.type = :group GROUP BY pc.name")
    public List<ProductColor> findColorOptions(@Param("category") String category, @Param("group") EType group);

    @Query("FROM ProductColor pc WHERE pc.product.category.id = 1 GROUP BY pc.name")
    public List<ProductColor> findColorOptions();
}
