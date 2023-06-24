package com.example.demo.application.image;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.mock.web.MockMultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class ImageStorageTest {

    private ImageStorage imageStorage;

    @BeforeEach
    void setUp() {
        imageStorage = mock(ImageStorage.class);
    }

    @Test
    @DisplayName("Image 파일 저장")
    void save() throws Exception {
        String filename = "src/test/resources/files/test.jpg";
        MockMultipartFile file = new MockMultipartFile(
                "image", "test.jpg", "image/jpeg",
                new FileInputStream(filename));

        imageStorage.save(file.getBytes(), "data/image.jpg");

        verify(imageStorage).save(any(), any());
    }


}