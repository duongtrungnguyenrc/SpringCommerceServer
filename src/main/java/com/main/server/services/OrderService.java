package com.main.server.services;

import com.main.server.configuration.security.services.UserDetailsImpl;
import com.main.server.mapping.InvoiceMapping;
import com.main.server.mapping.ProductMapping;
import com.main.server.models.dto.InvoiceDTO;
import com.main.server.models.entities.Invoice;
import com.main.server.models.entities.Product;
import com.main.server.models.entities.User;
import com.main.server.models.enumerations.EOrderStatus;
import com.main.server.models.request.ConfirmOrderRequest;
import com.main.server.models.request.CreateOrderRequest;
import com.main.server.models.response.Response;
import com.main.server.repositories.InvoiceRepository;
import com.main.server.repositories.ProductRepository;
import com.main.server.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class OrderService {
    @Autowired
    InvoiceRepository invoiceRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    public Object getAll(int page, int limit) {
       try {
           Pageable pageable = PageRequest.of(page, limit);
           Page<Invoice> invoices = invoiceRepository.findAll(pageable);
           Map<String, Object> data = new HashMap<>();
           data.put("orders", invoiceRepository.findAll().stream().map(InvoiceMapping::bind).toList());
           data.put("page", page);
           data.put("totalPages", (int) Math.ceil((double) invoices.getTotalElements() / limit));

           return ResponseEntity.ok(
                   new Response(
                           "Lấy dữ liệu hoàn tất!",
                           data
                   )
           );
       }
       catch (Exception e) {
           return ResponseEntity.badRequest().body(
                   new Response(
                           "Lấy dữ liệu thất bại: " + e.getMessage(),
                           null
                   )
           );
       }
    }

    public Object create(CreateOrderRequest createOrderRequest) {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal instanceof UserDetailsImpl) {
            User user = userRepository.findById(((UserDetailsImpl) principal).getId()).orElse(null);

            List<Product> products = new ArrayList<>();
            createOrderRequest.getProductIds().forEach(id -> {
                productRepository.findById(id).ifPresent(products::add);
            });

            try {
                Invoice invoice = invoiceRepository.save(new Invoice(null, new Date(), EOrderStatus.PENDING, products, user));
                return ResponseEntity.ok(
                        new Response(
                                "Tạo đơn hàng thành công",
                               true
                        )
                );
            } catch (Exception e) {
                return ResponseEntity.badRequest().body(
                        new Response(
                                "Tạo đơn hàng thất bại: " + e.getMessage(),
                                null
                        )
                );
            }
        }
        else {
            return ResponseEntity.status(401).body(
                    new Response(
                            "Tạo đơn hàng thất bại!",
                            null
                    )
            );
        }
    }

    public Object confirm(ConfirmOrderRequest confirmOrderRequest) {
        Invoice invoice = invoiceRepository.findById(confirmOrderRequest.getOrderId()).orElse(null);
        if(invoice != null) {
            switch (invoice.getStatus()) {
                case PENDING -> invoice.setStatus(EOrderStatus.TRANSPORTING);
                case TRANSPORTING -> invoice.setStatus(EOrderStatus.COMPLETED);
            }
            try {
                invoiceRepository.save(invoice);
                return ResponseEntity.ok(
                        new Response(
                                "Cập nhật trạng thái đơn hàng thành công",
                                true
                        )
                );
            }
            catch (Exception e) {
                return ResponseEntity.badRequest().body(
                        new Response(
                                "Không thể cập nhật trạng thái đơn hàng: " + e.getMessage(),
                                false
                        )
                );
            }
        }
        return ResponseEntity.badRequest().body(
                new Response(
                        "Đơn hàng không tồn tại!",
                        false
                )
        );
    }

    public Object reject(ConfirmOrderRequest confirmOrderRequest) {
        Invoice invoice = invoiceRepository.findById(confirmOrderRequest.getOrderId()).orElse(null);
        if(invoice != null) {
            invoice.setStatus(EOrderStatus.REJECTED);
            try {
                invoiceRepository.save(invoice);
                return ResponseEntity.ok(
                        new Response(
                                "Cập nhật trạng thái đơn hàng thành công",
                                true
                        )
                );
            }
            catch (Exception e) {
                return ResponseEntity.badRequest().body(
                        new Response(
                                "Không thể cập nhật trạng thái đơn hàng: " + e.getMessage(),
                                false
                        )
                );
            }
        }
        return ResponseEntity.badRequest().body(
                new Response(
                        "Đơn hàng không tồn tại!",
                        false
                )
        );
    }
}
