package com.example.demo.infrastructure;

import com.example.demo.dtos.ProductListDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.util.List;

@Component
public class ProductDtoFetcher {
    private final JdbcTemplate jdbcTemplate;

    public ProductDtoFetcher(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public ProductListDto fetchProductListDto() {
        String sql = """
            SELECT *
            FROM products
            ORDER BY products.id DESC
            """;

        List<ProductListDto.ProductDto> productDtos = jdbcTemplate.query(
            sql,
            (ResultSet resultSet, int rowNum) ->
                new ProductListDto.ProductDto(
                    resultSet.getString("id"),
                    resultSet.getString("name"),
                    resultSet.getLong("price"),
                        resultSet.getString("image")
                )
        );

        return new ProductListDto(productDtos);
    }
}
