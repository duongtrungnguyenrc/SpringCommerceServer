package com.main.server.controller;

import com.main.server.model.response.Response;
import com.main.server.services.ProductService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/all")
    public Object getAll(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "limit", required = false) Integer limit) {
        return ResponseEntity.ok(
                new Response(
                        "Successfully to get all products",
                        productService.getALlProducts(page, limit)
                )
        );
    }

    @GetMapping("/detail")
    public Object getDetail(@Nullable @RequestParam(value = "id") Integer id) {
        if (id == null) {
            return ResponseEntity.badRequest().body(
                    new Response(
                            "Missing required field!",
                            null
                    )
            );
        }
        return ResponseEntity.ok(
                new Response(
                        "Successfully to get all products!",
                        productService.getProductById(id)
                )
        );
    }

//    @GetMapping("/alll")
//    public Object getDetail(@Nullable @RequestParam(value = "color") String color) {
//        return ResponseEntity.ok(
//                new Response(
//                        "Successfully to get all products!",
//                        productService.getProductsByColor(color)
//                )
//        );
//    }
}
