package com.example.demo.models;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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

    @Column(name = "image")
    private String image;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "price"))
    private Money price;

    private Product() {
    }

    public Product(ProductId id, String name, String image, Money price) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.price = price;
    }

    public static Product create(String name, String image, Money price) {
        return new Product(ProductId.generate(), name, image, price);
    }

    public ProductId id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String image() {
        return image;
    }

    public Money price() {
        return price;
    }
}
