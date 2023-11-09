package com.main.server.models.dto;

import lombok.Data;
import java.util.Date;

@Data
public class ProductRatingDTO {
    private int id;
    private String comment;
    private double star;
    private Date time;
}
