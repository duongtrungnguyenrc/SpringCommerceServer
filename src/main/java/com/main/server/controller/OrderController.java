package com.main.server.controller;

import com.main.server.models.request.ConfirmOrderRequest;
import com.main.server.models.request.CreateOrderRequest;
import com.main.server.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public Object getAllOrders(@RequestParam(value = "page", required = false) Integer page,
                               @RequestParam(value = "limit", required = false) Integer limit) {
        return orderService.getAll(page, limit);
    }

    @PreAuthorize("hasRole('USER')" )
    @PostMapping("/create")
    public Object createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
        return orderService.create(createOrderRequest);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/confirm")
    public Object confirmOrder(@RequestBody ConfirmOrderRequest confirmOrderRequest) {
        return orderService.confirm(confirmOrderRequest);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/reject")
    public Object rejectOrder(@RequestBody ConfirmOrderRequest confirmOrderRequest) {
        return orderService.reject(confirmOrderRequest);
    }
}
