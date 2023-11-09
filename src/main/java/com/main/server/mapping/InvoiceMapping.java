package com.main.server.mapping;

import com.main.server.models.dto.InvoiceDTO;
import com.main.server.models.dto.ProductDTO;
import com.main.server.models.entities.Invoice;
import com.main.server.models.entities.Product;

import java.util.ArrayList;
import java.util.List;

public class InvoiceMapping {

    public static InvoiceDTO bind(Invoice invoiceEntity) {
        if(invoiceEntity != null) {
            InvoiceDTO invoice = new InvoiceDTO();
            invoice.setId(invoiceEntity.getId());
            invoice.setTime(invoiceEntity.getTime());
            invoice.setSumPrice(invoiceEntity.getSumPrice());
            List<ProductDTO> products = new ArrayList<>();
            for (Product product : invoiceEntity.getProducts()) {
                products.add(ProductMapping.bind(product));
            }
            invoice.setProducts(products);
            invoice.setUser(invoiceEntity.getUser());

            return invoice;
        }
        return null;
    }
}
