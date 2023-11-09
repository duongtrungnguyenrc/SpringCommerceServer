package com.main.server.models.entities;

import com.main.server.models.enumerations.EType;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Collection;

@Data
@Entity
@Table
public class ProductGroup {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Collection<ProductCategory> categories;

    @Column
    private String name;

    @Column
    @Enumerated(EnumType.STRING)
    private EType type;
}
