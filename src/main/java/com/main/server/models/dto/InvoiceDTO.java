package com.main.server.models.dto;

import com.main.server.models.entities.User;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class InvoiceDTO {
    private int id;
    private Date time;
    private double sumPrice;
    private List<ProductDTO> products;
    private User user;
}
