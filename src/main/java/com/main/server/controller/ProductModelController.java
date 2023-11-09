package com.main.server.controller;

import com.main.server.models.response.Response;
import com.main.server.services.ProductModelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin("*")
@RestController
@RequestMapping("api/product-model")
public class ProductModelController {
    @Autowired
    ProductModelService productModelService;
    @GetMapping("/all")
    public Object getAllModels() {
        return ResponseEntity.ok(
                new Response(
                        "Successfully to get all models!",
                        productModelService.gelAll()
                )
        );
    }
}
