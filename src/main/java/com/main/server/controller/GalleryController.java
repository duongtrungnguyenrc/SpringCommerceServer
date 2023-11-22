package com.main.server.controller;

import com.main.server.services.GalleryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@CrossOrigin("*")
@RestController
@RequestMapping("/api/gallery")
public class GalleryController {
    @Autowired
    GalleryService galleryService;
    @GetMapping("/images")
    public Object getGalleryImages() {
        return galleryService.getGalleryImages();
    }
}
