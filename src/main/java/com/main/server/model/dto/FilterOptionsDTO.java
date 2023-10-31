package com.main.server.model.dto;

import com.main.server.model.entities.ProductSize;
import lombok.Data;

import java.util.List;

@Data
public class FilterOptionsDTO {
    private List<ProductSizeDTO> sizes;
    private List<ProductColorDTO> colors;
}
