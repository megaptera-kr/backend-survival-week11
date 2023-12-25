package com.example.demo.controllers;

import com.example.demo.application.product.CreateProductService;
import com.example.demo.application.product.GetProductListService;
import com.example.demo.dtos.CreateProductDto;
import com.example.demo.dtos.ProductListDto;
import com.example.demo.models.Money;
import com.example.demo.utils.ImageStorage;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("products")
@CrossOrigin
public class ProductController {
    private final GetProductListService getProductListService;
    private final CreateProductService createProductService;
    private final ImageStorage imageStorage;


    public ProductController(GetProductListService getProductListService,
                             CreateProductService createProductService, ImageStorage imageStorage) {
        this.getProductListService = getProductListService;
        this.createProductService = createProductService;
        this.imageStorage = imageStorage;
    }

    @GetMapping
    public ProductListDto list() {
        return getProductListService.getProductListDto();
    }

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@ModelAttribute CreateProductDto dto)
            throws IOException {
        String name = dto.name().strip();
        String imageUrl = imageStorage.save(dto.image());
        Money price = new Money(dto.price());

        createProductService.createProduct(name, imageUrl, price);
    }
}
