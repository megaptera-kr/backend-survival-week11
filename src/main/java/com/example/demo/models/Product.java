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

    @Embedded
    @Column(name= "image")
    private Image image;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    @Embedded
    @AttributeOverride(name = "value", column = @Column(name = "price"))
    private Money price;

    private Product() {
    }

    public Product(ProductId id, String name, Money price, String image) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.image = new Image(image);
    }

    public static Product create(String name, Money price) {
        return new Product(ProductId.generate(), name, price, Image.generate());
    }

    public ProductId id() {
        return id;
    }

    public String name() {
        return name;
    }

    public Image image() { return image;}

    public Money price() {
        return price;
    }
}
