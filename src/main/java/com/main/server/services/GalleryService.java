package com.main.server.services;

import com.main.server.mapping.ProductImageMapping;
import com.main.server.models.dto.ProductImageDTO;
import com.main.server.models.response.Response;
import com.main.server.repositories.ProductImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GalleryService {
    @Autowired
    ProductImageRepository productImageRepository;

    public Object getGalleryImages() {
        try {
            List<ProductImageDTO> images = productImageRepository.findRandom().stream().map(ProductImageMapping::bind).toList();
            return ResponseEntity.ok(
                    new Response(
                            "Lấy dữ liệu thành công",
                            images
                    )
            );
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    new Response(
                            "Không thể truy vấn tài nguyên: " + e.getMessage(),
                            null
                    )
            );
        }
    }
}
