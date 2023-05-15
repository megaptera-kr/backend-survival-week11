package com.example.demo.application.cart;

import com.example.demo.models.Cart;
import com.example.demo.models.CartId;
import com.example.demo.models.LineItemId;
import com.example.demo.repositories.CartRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ChangeCartItemQuantityService {
    private final CartRepository cartRepository;

    public ChangeCartItemQuantityService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public void changeQuantity(LineItemId lineItemId, int quantity) {
        Cart cart = cartRepository.findById(CartId.DEFAULT).get();

        cart.changeLineItemQuantity(lineItemId, quantity);
    }
}
