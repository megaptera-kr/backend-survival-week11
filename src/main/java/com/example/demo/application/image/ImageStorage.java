package com.example.demo.application.image;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class ImageStorage {
    public String save(byte[] bytes, String filename) {
        File file = new File("data/" + filename);

        try (OutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(bytes);
        } catch (IOException e) {
            new RuntimeException(e);
        }
        return "data/" + filename;
    }
}
