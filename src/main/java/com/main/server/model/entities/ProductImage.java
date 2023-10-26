package com.main.server.model.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table
public class ProductImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String name;

    @Column
    private  String src;

    @ManyToOne
    private Product product;
}
