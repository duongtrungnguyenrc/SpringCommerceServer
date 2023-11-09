package com.main.server.controller;

import com.main.server.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/order")
public class OrderController {
    @Autowired
    OrderService orderService;
    @GetMapping("/all")
    public Object getAllOrders() {
        return orderService.getAll();
    }
}
