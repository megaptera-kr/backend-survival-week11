package com.example.demo.models;

import java.time.LocalDateTime;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "products")
public class Product {
    @EmbeddedId
    private ProductId id;

    @Column(name = "name")
    private String name;

    @Column(name = "image_url")
    private String imageUrl;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "price"))
    private Money price;

    private Product() {
    }

    public Product(ProductId id, String name, String imageUrl, Money price) {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.price = price;
    }

    public static Product create(String name, String ImageUrl, Money price ) {
        return new Product(ProductId.generate(), name, ImageUrl, price);
    }

    public ProductId id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String imageUrl() {
        return imageUrl;
    }

    public Money price() {
        return price;
    }
}