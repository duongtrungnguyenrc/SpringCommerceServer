package com.main.server.model.dto;

import lombok.Data;
import java.util.Date;

@Data
public class ProductRatingDTO {
    private int id;
    private String comment;
    private double star;
    private Date time;
}
