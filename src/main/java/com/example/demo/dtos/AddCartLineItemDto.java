package com.example.demo.dtos;

public record AddCartLineItemDto(
    String productId,
    int quantity
) {
}
