package com.main.server.model.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class ProductSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column
    private String name;

    @Column
    private double extraCoefficient;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
}
