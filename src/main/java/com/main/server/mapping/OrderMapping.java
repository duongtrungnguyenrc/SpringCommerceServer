package com.main.server.mapping;

import com.main.server.models.dto.InvoiceDTO;
import com.main.server.models.dto.ProductDTO;
import com.main.server.models.entities.Order;
import com.main.server.models.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderMapping {

    public static InvoiceDTO bind(Order orderEntity) {
        if(orderEntity != null) {
            InvoiceDTO invoice = new InvoiceDTO();
            invoice.setId(orderEntity.getId());
            invoice.setTime(orderEntity.getTime());
            invoice.setStatus(orderEntity.getStatus().toString());
            List<ProductDTO> products = new ArrayList<>();
            for (Product product : orderEntity.getProducts()) {
                products.add(ProductMapping.bind(product));
            }
            invoice.setProducts(products);
            invoice.setPrice(products.stream().mapToDouble(ProductDTO::getSalePrice).sum());
            invoice.setUser(orderEntity.getUser());

            return invoice;
        }
        return null;
    }
}
