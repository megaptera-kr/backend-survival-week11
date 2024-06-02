package com.example.demo.models;

import io.hypersistence.tsid.TSID;
import jakarta.persistence.Column;

import java.util.Objects;

public class Image {
    @Column(name = "image")
    private String image;

    private Image() {

    }

    public Image(String image) {
        this.image = image;
    }

    public static String generate(){
        String id = TSID.Factory.getTsid().toString();
        return "data/" + id + ".jpg";
    }

    @Override
    public String toString() {
        return this.image;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Image image1 = (Image) o;
        return Objects.equals(image, image1.image);
    }

    @Override
    public int hashCode() {
        return Objects.hash(image);
    }
}
