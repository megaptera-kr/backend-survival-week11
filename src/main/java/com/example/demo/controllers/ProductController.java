package com.example.demo.controllers;

import com.example.demo.application.image.ImageStorage;
import com.example.demo.application.product.CreateProductService;
import com.example.demo.application.product.GetProductListService;
import com.example.demo.dtos.CreateProductDto;
import com.example.demo.dtos.ProductListDto;
import com.example.demo.models.Money;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
    public void create(@ModelAttribute CreateProductDto dto) {
        String name = dto.name().strip();
        Money price = new Money(dto.price());
        MultipartFile image = dto.image();
        String filePath = "";
        try {
            filePath = imageStorage.save(image.getBytes(), image.getOriginalFilename());
        } catch (IOException e) {
            new RuntimeException(e);
        }
        createProductService.createProduct(name, filePath, price);
    }
}
