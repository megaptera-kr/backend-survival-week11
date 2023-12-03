package com.example.demo.infrastructure;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class ImageStorage {
    public String upload(MultipartFile image) {
        return "data/%s".formatted(image.getOriginalFilename());
    }
}
