package com.example.demo.models;

import jakarta.persistence.Embeddable;

@Embeddable
public class CartId extends EntityId {
    // TODO: Delete this! (카트가 하나만 존재한다고 가정)
    public static final CartId DEFAULT = new CartId("0BV000000CART");

    private CartId() {
        super();
    }

    public CartId(String value) {
        super(value);
    }

    public static CartId generate() {
        return new CartId(newTsid());
    }
}
