package com.main.server.models.dto;

import lombok.Data;

import java.util.List;

@Data
public class FilterOptionsDTO {
    private List<ProductSizeDTO> sizes;
    private List<ProductColorDTO> colors;
}
