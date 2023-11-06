package com.example.demo.utils;

import io.hypersistence.tsid.TSID;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Component
public class ImageStorage {
    public String save(MultipartFile multipartFile) {

        if(multipartFile == null || multipartFile.isEmpty()) {
            return "No Image";
        }

        String id = TSID.Factory.getTsid().toString();

        String filename = "data/" + id + ".jpg";

        File file = new File(filename);

        try (OutputStream outputStream =
                     new FileOutputStream(file)) {
            outputStream.write(multipartFile.getBytes());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return file.getPath();
    }
}
