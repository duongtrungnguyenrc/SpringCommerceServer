package com.main.server.controller;


import com.main.server.models.request.DeleteProductRequest;
import com.main.server.models.request.NewProductRequest;
import com.main.server.models.request.UpdateProductRequest;
import com.main.server.models.response.Response;
import com.main.server.services.ProductService;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
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
                        "Lấy dữ liệu thành công!",
                        productService.getAll(page, limit, group, category, color, size, tag, minPrice, maxPrice)
                )
        );
    }

    @GetMapping("/all-by-tag")
    public Object getByTag(@RequestParam(value = "tag") String tag) {
        return productService.getSpecialProductsByTag(tag);
    }

    @GetMapping("/detail")
    public Object getDetail(@Nullable @RequestParam(value = "id") Integer id) {
        if (id == null) {
            return ResponseEntity.badRequest().body(
                    new Response(
                            "Thiếu trường thông tin!",
                            null
                    )
            );
        }
        return ResponseEntity.ok(
                new Response(
                        "Lấy dữ liệu thành công!",
                        productService.getProductById(id)
                )
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/private/all")
    public Object getAllAuthorize(@RequestParam(value = "page", required = false) Integer page,
                                  @RequestParam(value = "limit", required = false) Integer limit,
                                  @RequestParam(value = "group", required = false) String group,
                                  @RequestParam(value = "category", required = false) String category,
                                  @RequestParam(value = "color", required = false) String color,
                                  @RequestParam(value = "size", required = false) String size,
                                  @RequestParam(value = "tag", required = false) String tag,
                                  @RequestParam(value = "min", required = false) String minPrice,
                                  @RequestParam(value = "max", required = false) String maxPrice) {

        return getAll(page, limit, group, category, color, size, tag, minPrice, maxPrice);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public Object addNewProduct(@RequestBody NewProductRequest newProduct) {
        return productService.addProduct(newProduct);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add-multiple")
    public Object addNewProducts(@RequestBody List<NewProductRequest> newProducts) {
        newProducts.forEach(newProduct -> {
            productService.addProduct(newProduct);
        });
        return ResponseEntity.ok(
                new Response(
                        "Thêm sản phẩm hoàn tất!",
                        true
                )
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/update")
    public Object updateProduct(@RequestBody UpdateProductRequest updateProduct) {
        return productService.updateProduct(updateProduct);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/delete")
    public Object deleteProduct(@RequestBody DeleteProductRequest deleteProductRequest) {
        return productService.deleteProduct(deleteProductRequest.getId());
    }

    @GetMapping("/filter")
    public Object getAllFilter(@Nullable @RequestParam String category, @Nullable @RequestParam String group) {
        return productService.getFilterOptions(category, group);
    }

}
