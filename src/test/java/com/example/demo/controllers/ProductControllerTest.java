package com.example.demo.controllers;

import com.example.demo.application.product.CreateProductService;
import com.example.demo.application.product.GetProductListService;
import com.example.demo.dtos.ProductListDto;
import com.example.demo.models.Money;
import com.example.demo.utils.ImageStorage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.io.FileInputStream;
import java.util.List;

import static com.example.demo.controllers.helpers.ResultMatchers.contentContains;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
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
    @DisplayName("POST /products - with file")
    void create() throws Exception {

        String name = "멋진 제품";
        Long price = 100_000L;
        String filename = "src/test/resources/files/test.jpg";

        MockMultipartFile file = new MockMultipartFile(
                "image", "test.jpg", "image/jpeg",
                new FileInputStream(filename));

        mockMvc.perform(
                multipart("/products")
                        .file(file)
                        .param("name", name)
                        .param("price", String.valueOf(price))
        ).andExpect(status().isCreated());

        verify(imageStorage).save(any());
        verify(createProductService)
                .createProduct("멋진 제품", new Money(100_000L), "test.jpg");
    }

    @Test
    @DisplayName("POST /products - without file")
    void createWithoutFile() throws Exception {

        String name = "멋진 제품";
        Long price = 100_000L;
//        String filename = "src/test/resources/files/test.jpg";

//        MockMultipartFile file = new MockMultipartFile(
//                "image", "test.jpg", "image/jpeg",
//                new FileInputStream(filename));

        mockMvc.perform(
                multipart("/products")
                        .param("name", name)
                        .param("price", String.valueOf(price))
        ).andExpect(status().isCreated());

        verify(imageStorage, never()).save(any());
        verify(createProductService)
                .createProduct("멋진 제품", new Money(100_000L), "No image");
    }
}
