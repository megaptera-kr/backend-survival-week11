package com.example.demo.controllers;

import com.example.demo.application.product.CreateProductService;
import com.example.demo.application.product.GetProductListService;
import com.example.demo.dtos.ProductListDto;
import com.example.demo.infrastructure.ImageStorage;
import com.example.demo.models.Money;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;

import java.io.FileInputStream;
import java.util.List;

import static com.example.demo.controllers.helpers.ResultMatchers.contentContains;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@ActiveProfiles("test")
class ProductControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private GetProductListService getProductListService;

    @MockBean
    private CreateProductService createProductService;
    @MockBean
    private ImageStorage imageStorage;

    @Test
    @DisplayName("GET /products")
    void list() throws Exception {
        ProductListDto.ProductDto productDto =
                new ProductListDto.ProductDto("test-id", "제품", 100_000L);

        given(getProductListService.getProductListDto()).willReturn(
                new ProductListDto(List.of(productDto)));

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(contentContains("제품"));
    }

    @Test
    @DisplayName("POST /products")
    void create() throws Exception {
        MockMultipartFile image = new MockMultipartFile(
                "test",
                "test.jpg",
                MediaType.IMAGE_JPEG_VALUE,
                new FileInputStream("src/test/resources/files/test.jpg")
        );

        RequestBuilder requestBuilder = multipart("/products")
                .file(image)
                .param("name", "멋진 제품")
                .param("price", Long.toString(100_000L));

        given(imageStorage.upload(any())).willReturn("data/test.jpg");

        mockMvc.perform(requestBuilder)
                .andExpect(status().isCreated());

        verify(createProductService)
                .createProduct("멋진 제품", new Money(100_000L), "data/test.jpg");
    }
}
