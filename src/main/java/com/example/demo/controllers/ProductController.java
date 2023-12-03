package com.example.demo.controllers;

import com.example.demo.application.product.CreateProductService;
import com.example.demo.application.product.GetProductListService;
import com.example.demo.dtos.CreateProductDto;
import com.example.demo.dtos.ProductListDto;
import com.example.demo.infrastructure.ImageStorage;
import com.example.demo.models.Money;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("products")
@CrossOrigin
public class ProductController {
    private final GetProductListService getProductListService;
    private final CreateProductService createProductService;
    private final ImageStorage imageStorage;

    public ProductController(
            GetProductListService getProductListService,
            CreateProductService createProductService,
            ImageStorage imageStorage
    ) {
        this.getProductListService = getProductListService;
        this.createProductService = createProductService;
        this.imageStorage = imageStorage;
    }

    @GetMapping
    public ProductListDto list() {
        return getProductListService.getProductListDto();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestBody CreateProductDto dto) {
        String name = dto.name().strip();
        Money price = new Money(dto.price());
        String imageUrl = imageStorage.upload(dto.image());

        createProductService.createProduct(name, price, imageUrl);
    }
}
