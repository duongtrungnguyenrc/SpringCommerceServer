package com.main.server.models.entities;

import com.main.server.models.enumerations.ETag;
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

    @Column(columnDefinition = "boolean default true")
    private boolean isValid;

    @Column(columnDefinition = "double default 0.0")
    private Double basePrice = 0.0;

    @Column(columnDefinition = "double default 0.0")
    private Double salePrice = 0.0;

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
    private ProductCategory category;

    @ManyToOne
    private ProductModel model;

    @ManyToMany(mappedBy = "products")
    private Collection<Invoice> invoices;
}
