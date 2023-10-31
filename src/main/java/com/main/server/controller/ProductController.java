package com.main.server.controller;


import com.main.server.model.enumerations.ETag;
import com.main.server.model.request.NewProductRequest;
import com.main.server.model.response.Response;
import com.main.server.services.ProductService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @GetMapping("/all")
    public Object getAll(@RequestParam(value = "page", required = false) Integer page,
                         @RequestParam(value = "limit", required = false) Integer limit,
                         @RequestParam(value = "group", required = false) String group,
                         @RequestParam(value = "category", required = false) String category,
                         @RequestParam(value = "color", required = false) String color,
                         @RequestParam(value = "size", required = false) String size,
                         @RequestParam(value = "tag", required = false) String tag,
                         @RequestParam(value = "min", required = false) String minPrice,
                         @RequestParam(value = "max", required = false) String maxPrice) {
        return ResponseEntity.ok(
                new Response(
                        "Successfully to get all products",
                        productService.getAllProducts(page, limit, group, category, color, size, tag, minPrice, maxPrice)
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

    @PostMapping("/add")
    public Object addNewProduct(@RequestBody NewProductRequest newProduct) {
        return ResponseEntity.ok(
                new Response(
                        "Successfully to add new product!",
                        productService.addProduct(newProduct)
                )
        );
    }

    @PostMapping("/addAll")
    public Object addNewProducts(@RequestBody List<NewProductRequest> newProducts) {
        newProducts.forEach(newProduct -> {
            productService.addProduct(newProduct);
        });
        return ResponseEntity.ok(
                new Response(
                        "Successfully to add all new product!s",
                        true
                )
        );
    }


    @GetMapping("/filter")
    public Object getAllFilter(@RequestParam String category) {
        return productService.getFilterOptions(category);
    }

}
