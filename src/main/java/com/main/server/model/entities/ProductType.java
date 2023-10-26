package com.main.server.model.entities;

import com.main.server.model.enumerations.EType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Data
@Entity
@Table
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "type", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<ProductGroup> groups;

    @Column
    private String name;

}
