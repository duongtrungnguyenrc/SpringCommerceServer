package com.main.server.repositories;

import com.main.server.models.entities.Product;
import com.main.server.models.enumerations.ETag;
import com.main.server.models.enumerations.EType;
import jakarta.annotation.Nullable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query("FROM Product p JOIN p.category c WHERE c.group.type = :group AND c.name LIKE %:category% AND p.isValid = true")
    Page<Product> findByCategory(@Param("group") EType group, @Param("category") String category, @Nullable Pageable pageable);

    @Query("FROM Product p JOIN p.category c WHERE c.id = 1 AND p.isValid = true")
    Page<Product> findByCategory(@Nullable Pageable pageable);


    ////////////////////


    @Query("FROM Product p JOIN p.category c WHERE c.group.type = :group AND c.name LIKE %:category% AND p.salePrice >= :min AND p.salePrice <= :max AND p.isValid = true")
    Page<Product> findByRangePrice(@Param("group") EType group,
                                   @Param("category") String category,
                                   @Param("min") double minPrice,
                                   @Param("max") double maxPrice,
                                   @Nullable Pageable pageable);
    @Query("FROM Product p JOIN p.category c JOIN p.productColors pc WHERE c.group.type = :group AND c.name LIKE %:category% AND pc.name LIKE %:color% AND p.isValid = true")
    Page<Product> findByColor(@Param("group") EType group,
                              @Param("category") String category,
                              @Param("color") String color,
                              @Nullable Pageable pageable);
    @Query("FROM Product p JOIN p.category c JOIN p.productSizes pc WHERE c.group.type = :group AND c.name LIKE %:category% AND pc.name LIKE %:size% AND p.isValid = true")
    Page<Product> findBySize(@Param("group") EType group,
                             @Param("category") String category,
                             @Param("size") String size,
                             @Nullable Pageable pageable);
    @Query("FROM Product p JOIN p.category c WHERE c.group.type = :group AND c.name LIKE %:category% AND  p.tag = :tag AND p.isValid = true")
    Page<Product> findByTag(@Param("group") EType group,
                            @Param("category") String category,
                            @Param("tag") ETag tag,
                            @Nullable Pageable pageable);

    @Query("FROM Product p WHERE p.tag = :tag AND p.category.group.type = :type ORDER BY RAND()")
    Page<Product> findByTag(@Param("tag") ETag tag, @Param("type") EType type, Pageable pageable);


    ////////////////////


    @Query("FROM Product p JOIN p.category c JOIN p.productColors pc JOIN p.productSizes ps WHERE c.group.type = :group AND c.name LIKE %:category% AND  pc.name LIKE %:color% AND ps.name LIKE %:size% AND p.isValid = true")
    Page<Product> findByColorAndSize(@Param("group") EType group,
                                     @Param("category") String category,
                                     @Param("color") String color,
                                     @Param("size") String size,
                                     @Nullable Pageable pageable);
    @Query("FROM Product p JOIN p.category c JOIN p.productColors pc WHERE c.group.type = :group AND c.name LIKE %:category% AND  pc.name LIKE %:color% AND p.tag = :tag AND p.isValid = true")
    Page<Product> findByColorAndTag(@Param("group") EType group,
                                    @Param("category") String category,
                                    @Param("color") String color,
                                    @Param("tag") ETag tag,
                                    @Nullable Pageable pageable);
    @Query("FROM Product p JOIN p.category c JOIN p.productSizes ps WHERE c.group.type = :group AND c.name LIKE %:category% AND ps.name LIKE %:size% AND p.tag = :tag AND p.isValid = true")
    Page<Product> findBySizeAndTag(@Param("group") EType group,
                                   @Param("category") String category,
                                   @Param("size") String size, ETag tag,
                                   @Nullable Pageable pageable);
    @Query("FROM Product p JOIN p.category c JOIN p.productSizes ps WHERE c.group.type = :group AND c.name LIKE %:category% AND ps.name LIKE %:size% AND p.salePrice >= :min AND p.salePrice <= :max AND p.isValid = true")
    Page<Product> findBySizeAndRangePrice(@Param("group") EType group,
                                          @Param("category") String category,
                                          @Param("size") String size,
                                          @Param("min") double minPrice,
                                          @Param("max") double maxPrice,
                                          @Nullable Pageable pageable);
    @Query("FROM Product p JOIN p.category c JOIN p.productColors pc WHERE c.group.type = :group AND c.name LIKE %:category% AND pc.name LIKE %:color% AND p.salePrice >= :min AND p.salePrice <= :max AND p.isValid = true")
    Page<Product> findByColorAndRangePrice(@Param("group") EType group,
                                           @Param("category") String category,
                                           @Param("color") String color,
                                           @Param("min") double minPrice,
                                           @Param("max") double maxPrice,
                                           @Nullable Pageable pageable);

    @Query("FROM Product p JOIN p.category c JOIN p.productColors pc WHERE c.group.type = :group AND c.name LIKE %:category% AND p.tag = :tag AND p.salePrice >= :min AND p.salePrice <= :max AND p.isValid = true")
    Page<Product> findByTagAndRangePrice(@Param("group") EType group,
                                           @Param("category") String category,
                                           @Param("tag") ETag tag,
                                           @Param("min") double minPrice,
                                           @Param("max") double maxPrice,
                                           @Nullable Pageable pageable);


    ////////////////////


    @Query("FROM Product p JOIN p.category c JOIN p.productColors pc JOIN p.productSizes ps WHERE c.group.type = :group AND c.name LIKE %:category% AND pc.name LIKE %:color% AND ps.name LIKE %:size% AND p.tag = :tag AND p.isValid = true")
    Page<Product> findByColorAndSizeAndTag(@Param("group") EType group,
                                           @Param("category") String category,
                                           @Param("color") String color,
                                           @Param("size") String size,
                                           @Param("tag") ETag tag,
                                           @Nullable Pageable pageable);
    @Query("FROM Product p JOIN p.category c JOIN p.productColors pc JOIN p.productSizes ps WHERE c.group.type = :group AND c.name LIKE %:category% AND pc.name LIKE %:color% AND ps.name LIKE %:size% AND p.salePrice >= :minPrice AND p.salePrice <= :maxPrice AND p.isValid = true")
    Page<Product> findByColorAndSizeAndPrice(
            @Param("group") EType group,
            @Param("category") String category,
            @Param("color") String color,
            @Param("size") String size,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            @Nullable Pageable pageable);

    @Query("FROM Product p JOIN p.category c JOIN p.productColors pc WHERE c.group.type = :group AND c.name LIKE %:category% AND pc.name LIKE %:color% AND p.tag = :tag AND p.salePrice >= :min AND p.salePrice <= :max AND p.isValid = true")
    Page<Product> findByColorAndTagAndPrice(@Param("group") EType group,
                                           @Param("category") String category,
                                           @Param("color") String color,
                                           @Param("tag") ETag tag,
                                           @Param("min") double minPrice,
                                           @Param("max") double maxPrice,
                                           @Nullable Pageable pageable);
    @Query("FROM Product p JOIN p.category c JOIN p.productSizes ps WHERE c.group.type = :group AND c.name LIKE %:category% AND ps.name LIKE %:size% AND p.tag = :tag AND p.salePrice >= :min AND p.salePrice <= :max AND p.isValid = true")
    Page<Product> findBySizeAndTagAndPrice(@Param("group") EType group,
                                            @Param("category") String category,
                                            @Param("size") String size,
                                            @Param("tag") ETag tag,
                                            @Param("min") double minPrice,
                                            @Param("max") double maxPrice,
                                            @Nullable Pageable pageable);



    ////////////////////


    @Query("FROM Product p JOIN p.category c JOIN p.productColors pc JOIN p.productSizes ps WHERE c.group.type = :group AND c.name LIKE %:category% AND pc.name LIKE %:color% AND ps.name LIKE %:size% AND p.tag = :tag AND p.salePrice >= :minPrice AND p.salePrice <= :maxPrice AND p.isValid = true")
    Page<Product> findByColorAndSizeTagAndPrice(@Param("group") EType group,
                                                  @Param("category") String category,
                                                  @Param("color") String color,
                                                  @Param("size") String size,
                                                  @Param("tag") ETag tag,
                                                  @Param("minPrice") double minPrice,
                                                  @Param("maxPrice") double maxPrice,
                                                  @Nullable Pageable pageable);

    @Modifying
    @Query("UPDATE Product p SET p.isValid = false WHERE p.id = :id")
    void deleteById(@Param("id") int id);

}

