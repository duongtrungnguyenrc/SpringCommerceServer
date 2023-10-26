package com.main.server.model.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;
import java.util.Set;

@Data
@Entity
@Table
public class ProductModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String height;

    @Column
    private String weight;

    @Column
    private String threeRoundMeasurements;

    @OneToMany(mappedBy = "model", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<Product> products;
}
