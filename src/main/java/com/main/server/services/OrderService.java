package com.main.server.services;

import com.main.server.mapping.InvoiceMapping;
import com.main.server.models.dto.InvoiceDTO;
import com.main.server.models.entities.Invoice;
import com.main.server.models.response.Response;
import com.main.server.repositories.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    InvoiceRepository invoiceRepository;

    public Object getAll() {
        return ResponseEntity.ok(
                new Response(
                        "Lấy dữ liệu hoàn tất!",
                        invoiceRepository.findAll().stream().map(InvoiceMapping::bind)
                )
        );
    }

    public Object getOrderById(int id) {
        InvoiceDTO order = InvoiceMapping.bind(invoiceRepository.findById(id).orElse(null));

        if(order != null) {
            return ResponseEntity.ok(
                    new Response(
                            "lấy dữ liệu hoàn tất",
                            order
                    )
            );
        }
        else {
            return ResponseEntity.badRequest().body(
                    new Response(
                            "lấy dữ liệu thất bại",
                            order
                    )
            );
        }

    }
}
