package com.example.demo.backdoor;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("backdoor")
public class BackdoorController {
    private final JdbcTemplate jdbcTemplate;

    public BackdoorController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @GetMapping("setup-database")
    public String setupDatabase() {
        deleteLineItems();
        deleteCarts();
        deleteProducts();

        return "OK!";
    }

    private void deleteLineItems() {
        jdbcTemplate.update("DELETE FROM line_items");
    }

    private void deleteCarts() {
        jdbcTemplate.update("DELETE FROM carts");
    }

    private void deleteProducts() {
        jdbcTemplate.update("DELETE FROM products");
    }
}
