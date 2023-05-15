package com.example.demo.repositories;

import com.example.demo.models.Product;
import com.example.demo.models.ProductId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ProductRepository extends CrudRepository<Product, ProductId> {
    List<Product> findAll();
}
