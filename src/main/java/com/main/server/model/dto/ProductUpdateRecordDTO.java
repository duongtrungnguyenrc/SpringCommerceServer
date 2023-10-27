package com.main.server.model.dto;

import com.main.server.model.entities.Product;

import java.util.Date;

public class ProductUpdateRecordDTO {

    private Date time;
    private String description;
    private  Product product;
    public ProductUpdateRecordDTO(Date time, String description, Product product) {
        this.time = time;
        this.description = description;
        this.product = product;
    }
}
