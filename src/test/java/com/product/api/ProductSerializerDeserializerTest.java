package com.product.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.api.model.Product;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ProductSerializerDeserializerTest {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testSerialization() throws JsonProcessingException {
        Product product = new Product(UUID.randomUUID(), "IT-Care Laptop", "Gaming", 1299.99, true);
        String json = objectMapper.writeValueAsString(product);

        assertTrue(json.contains("IT-Care Laptop"));
        assertTrue(json.contains("Gaming"));
    }

    @Test
    void testDeserialization() throws JsonProcessingException {
        String json = """
                {
                  "id": "6d3f4f90-54a2-4f1e-a9d2-ecb251d48d88",
                  "name": "IT-Care Desktop",
                  "description": "Powerful PC",
                  "price": 899.99,
                  "available": true
                }
                """;

        Product product = objectMapper.readValue(json, Product.class);

        assertEquals("IT-Care Desktop", product.getName());
        assertEquals("Powerful PC", product.getDescription());
        assertEquals(899.99, product.getPrice());
        assertTrue(product.getAvailable());
    }
}
