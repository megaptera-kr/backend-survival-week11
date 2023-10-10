package com.example.demo.application.product;

import com.example.demo.models.Money;
import com.example.demo.models.Product;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.utils.ImageStorage;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Transactional
public class CreateProductService {
    private final ProductRepository productRepository;
    private final ImageStorage imageStorage;

    public CreateProductService(ProductRepository productRepository,
                                ImageStorage imageStorage) {
        this.productRepository = productRepository;
        this.imageStorage = imageStorage;
    }

    public Product createProduct(String name, MultipartFile multipartFile, Money price) {
        String filePath = imageStorage.save(multipartFile);

        Product product = Product.create(name, filePath, price);

        productRepository.save(product);

        return product;
    }
}
