package com.example.demo.models;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

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

    public static Product create(String name, String imageUrl, Money price) {
        return new Product(ProductId.generate(), name, imageUrl, price);
    }

    public ProductId id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String imageUrl() {return imageUrl;}

    public Money price() {
        return price;
    }
}
