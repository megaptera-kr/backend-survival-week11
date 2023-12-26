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

    @Column(name = "name", nullable = false)
    private String name;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "price", nullable = false))
    private Money price;

    @Column(name = "image", nullable = false)
    private String image;

    private Product() {
    }

    public Product(ProductId id, String name, Money price, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = image;
    }

    public static Product create(String name, Money price, String image) {
        return new Product(ProductId.generate(), name, price, image);
    }

    public ProductId id() {
        return id;
    }

    public String name() {
        return name;
    }

    public Money price() {
        return price;
    }

    public String image() {
        return image;
    }
}
