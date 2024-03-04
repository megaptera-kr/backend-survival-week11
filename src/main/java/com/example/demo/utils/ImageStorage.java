package com.example.demo.utils;

import io.hypersistence.tsid.TSID;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class ImageStorage {
    public String save(byte[] bytes) {
        String id = TSID.Factory.getTsid().toString();
        String url = "data/" + id + ".jpg";
        File file = new File(url);

        try (OutputStream outputStream = new FileOutputStream(file)) {
            outputStream.write(bytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return url;
    }
}
