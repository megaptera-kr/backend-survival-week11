package com.example.demo.dtos;

import java.util.List;

public class ProductListDto {
    private List<ProductDto> products;

    public ProductListDto(List<ProductDto> products) {
        this.products = products;
    }

    public List<ProductDto> getProducts() {
        return products;
    }

    // 오류 수정 커밋용 주석
    public record ProductDto(
            String id,
            String name,
            Long price,
            String image
    ) {
    }
}
