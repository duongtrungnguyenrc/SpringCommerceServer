package com.main.server.model.entities;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table
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
}
