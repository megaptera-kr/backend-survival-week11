package com.example.demo.models;

import org.junit.jupiter.api.Test;
import org.springframework.mock.web.MockMultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

class ProductTest {
    @Test
    void creation() throws IOException {

        Product product = Product.create("제품명", "data/image.jpg", new Money(123_456L));

        assertThat(product.id()).isNotNull();
        assertThat(product.name()).isEqualTo("제품명");
        assertThat(product.price()).isEqualTo(new Money(123_456L));
    }
}
