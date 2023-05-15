package com.example.demo.repositories;

import com.example.demo.models.Cart;
import com.example.demo.models.CartId;
import org.springframework.data.repository.CrudRepository;

public interface CartRepository extends CrudRepository<Cart, CartId> {
}
