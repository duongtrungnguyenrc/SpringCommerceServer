package com.main.server.model.entities;

import com.main.server.model.enumerations.ETag;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Data
@Entity
@Table
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private String description;

    @Column
    @Enumerated(EnumType.STRING)
    private ETag tag;

    @Column(columnDefinition = "boolean default 0")
    private double basePrice = 0.0;

    @Column(columnDefinition = "boolean default 0")
    private double salePrice = 0.0;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<ProductUpdateRecord> updateRecords;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<ProductSize> productSizes;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<ProductPreserveMethod> preserveMethods;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<ProductImage> productImages;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<ProductColor> productColors;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<ProductRating> productRatings;

    @ManyToOne
    private ProductGroup group;

    @ManyToOne
    private ProductModel model;
}
