package com.main.server.models.dto;

import com.main.server.models.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductUpdateRecordDTO {

    private Integer id;
    private Date time;
    private String description;
}
