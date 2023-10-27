package com.main.server.model.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdateRecord
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private Date time;

    @Column
    private String description;

    @ManyToOne
    private Product product;
    public ProductUpdateRecord(Date time, String description, Product product) {
        this.time = time;
        this.description = description;
        this.product = product;
    }
}
