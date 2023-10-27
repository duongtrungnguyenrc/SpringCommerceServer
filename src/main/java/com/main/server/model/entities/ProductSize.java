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

    @Column(columnDefinition = "boolean default 0.0")
    private double extraCoefficient = 0.0;

    @ManyToOne
    private Product product;
}
