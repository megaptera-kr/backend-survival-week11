package com.example.demo.infrastructure;

import io.hypersistence.tsid.TSID;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class ImageStorage {
    public String autoSave(byte[] bytes) {
        String id = new TSID.Factory().toString();
        File file = new File("data/" + id + ".jpg");

        try (OutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return file.getPath();
    }
}
