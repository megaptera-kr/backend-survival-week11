package com.example.demo.application.product;

import com.example.demo.models.Money;
import com.example.demo.models.Product;
import com.example.demo.repositories.ProductRepository;
import com.example.demo.utils.ImageStorage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class CreateProductServiceTest {
    private ProductRepository productRepository;

    private CreateProductService createProductService;

    private ImageStorage imageStorage;

    @BeforeEach
    void setUp() {
        productRepository = mock(ProductRepository.class);

        imageStorage = mock(ImageStorage.class);

        createProductService = new CreateProductService(productRepository, imageStorage);
    }

    @Test
    void createProduct() throws IOException {
        String name = "제-품";
        String filename = "src/test/resources/files/test.jpg";
        Money price = new Money(100_000L);

        MockMultipartFile file = new MockMultipartFile(
                "image", "test.jpg", "image/jpeg",
                new FileInputStream(filename));

        Product product = createProductService.createProduct(name, file, price);

        assertThat(product.name()).isEqualTo(name);
//        assertThat(product.image()).isEqualTo(filename);
        assertThat(product.price()).isEqualTo(price);

        verify(productRepository).save(product);
    }
}
