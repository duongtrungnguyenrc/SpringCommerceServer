package com.main.server.controller;

import com.main.server.model.response.Response;
import com.main.server.services.ProductGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin(value = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/product-group")
public class ProductGroupController {
    @Autowired
    ProductGroupService productGroupService;
    @GetMapping("/all")
    public Object getAllGroups() {
        return ResponseEntity.ok(
                new Response(
                        "successfully to get all product groups",
                        productGroupService.getAllGroups()
                )
        );
    }
}
