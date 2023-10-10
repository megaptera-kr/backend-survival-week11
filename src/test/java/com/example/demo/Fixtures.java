package com.example.demo;

import com.example.demo.models.Cart;
import com.example.demo.models.CartId;
import com.example.demo.models.LineItem;
import com.example.demo.models.LineItemId;
import com.example.demo.models.Money;
import com.example.demo.models.Product;
import com.example.demo.models.ProductId;

import java.io.IOException;
import java.util.List;

public class Fixtures {
    public static Product product() throws IOException {
        return product(1);
    }

    public static Product product(int number) throws IOException {
        ProductId productId = new ProductId("012300000000" + number);

        return new Product(
            productId, "Product #" + number, "data/image.jpg", new Money(123_000L));
    }

    public static Cart cart() {
        return cart(List.of());
    }

    public static Cart cart(List<Product> products) {
        CartId cartId = new CartId("0124000000001");
        Cart cart = new Cart(cartId);

        products.forEach(product -> {
            cart.addProduct(product, 1);
        });

        return cart;
    }

    public static LineItem lineItem() throws IOException {
        Product product = product();
        LineItemId lineItemid = new LineItemId("0125000000001");
        return new LineItem(lineItemid, product, 1);
    }
}
