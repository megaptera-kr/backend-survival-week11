package com.example.demo.application.product;

import com.example.demo.infrastructure.ImageStorage;
import com.example.demo.models.Money;
import com.example.demo.models.Product;
import com.example.demo.repositories.ProductRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@Transactional
public class CreateProductService {
    private final ProductRepository productRepository;
    private final ImageStorage imageStorage;

    public CreateProductService(
            ProductRepository productRepository,
            ImageStorage imageStorage) {
        this.productRepository = productRepository;
        this.imageStorage = imageStorage;
    }

    public Product createProduct(String name, Money price, MultipartFile multipartFile) throws RuntimeException {

        String fileName;

        try {
            fileName = imageStorage.autoSave(multipartFile.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Product product = Product.create(name, price, fileName);

        productRepository.save(product);

        return product;
    }
}
