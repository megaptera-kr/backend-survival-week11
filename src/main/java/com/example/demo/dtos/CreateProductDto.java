package com.example.demo.dtos;

import org.springframework.web.multipart.MultipartFile;

public record CreateProductDto(
        String name,
        MultipartFile image,
        Long price
) {
}
