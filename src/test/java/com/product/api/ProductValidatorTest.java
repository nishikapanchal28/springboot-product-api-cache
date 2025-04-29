package com.product.api;

import com.product.api.exception.ProductValidationException;
import com.product.api.model.Product;
import com.product.api.validator.ProductValidator;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class ProductValidatorTest {
    @Test
    void testValidProduct() {
        Product product = new Product(UUID.randomUUID(), "IT-Care Monitor", "Good display", 150.0, true);
        assertDoesNotThrow(() -> ProductValidator.validate(product));
    }

    @Test
    void testInvalidName() {
        Product product = new Product(UUID.randomUUID(), "Monitor", "Just a screen", 100.0, true);
        Exception ex = assertThrows(ProductValidationException.class, () -> ProductValidator.validate(product));
        assertTrue(ex.getMessage().contains("must contain 'IT-Care'"));
    }

    @Test
    void testInvalidPrice() {
        Product product = new Product(UUID.randomUUID(), "IT-Care Monitor", "This product is Expensive", -50.0, true);
        Exception ex = assertThrows(ProductValidationException.class, () -> ProductValidator.validate(product));
        assertTrue(ex.getMessage().contains("Price must be greater than zero"));
    }

    @Test
    void testNullName() {
        Product product = new Product(UUID.randomUUID(), null, "No name", 100.0, true);
        Exception ex = assertThrows(ProductValidationException.class, () -> ProductValidator.validate(product));
        assertTrue(ex.getMessage().contains("Product name is required."));
    }

}
