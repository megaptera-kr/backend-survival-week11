package com.example.demo.dtos;

import org.springframework.web.multipart.MultipartFile;

public record CreateProductDto(
    String name,
    Long price,

    MultipartFile image
) {
}
