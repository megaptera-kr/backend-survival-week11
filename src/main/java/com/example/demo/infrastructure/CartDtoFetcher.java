package com.example.demo.infrastructure;

import com.example.demo.dtos.CartDto;
import com.example.demo.models.CartId;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.List;

@Component
public class CartDtoFetcher {
    private final JdbcTemplate jdbcTemplate;

    public CartDtoFetcher(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public CartDto fetchCartDto(CartId cartId) {
        String sql = """
            SELECT
                *,
                products.name AS product_name2
            FROM line_items
            JOIN products ON line_items.product_id = products.id
            WHERE line_items.cart_id = ?
            ORDER BY line_items.id
            """;

        List<CartDto.LineItemDto> lineItemDtos = jdbcTemplate.query(
            sql,
            (ResultSet resultSet, int rowNum) -> new CartDto.LineItemDto(
                resultSet.getString("id"),
                resultSet.getString("product_name2"),
                resultSet.getLong("unit_price"),
                resultSet.getInt("quantity"),
                resultSet.getLong("total_price")
            ),
            cartId.toString()
        );

        return new CartDto(lineItemDtos);
    }
}
