package com.product.api;

import com.product.api.exception.ProductNotFoundException;
import com.product.api.model.Product;
import com.product.api.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

public class ProductServiceTest {
    private ProductService productService;

    @BeforeEach
    void setup() {
        productService = new ProductService();
    }

    @Test
    void testCreateAndGetProduct() {
        Product product = new Product(null, "Phone", "Smartphone", 699.99, true);
        Product created = productService.createProduct(product);

        Product fetched = productService.getProductById(created.getId());

        assertEquals("Phone", fetched.getName());
        assertTrue(fetched.getAvailable());
    }

    @Test
    void testGetProductNotFound() {
        UUID id = UUID.randomUUID();
        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(id));
    }

    @Test
    void testUpdateProduct() {
        Product product = productService.createProduct(new Product(null, "TV", "4K TV", 999.0, true));
        product.setPrice(899.0);

        Product updated = productService.updateProduct(product.getId(), product);

        assertEquals(899.0, updated.getPrice());
    }

    @Test
    void testDeleteProduct() {
        Product product = productService.createProduct(new Product(null, "Tablet", "Android", 299.0, true));
        productService.deleteProduct(product.getId());

        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(product.getId()));
    }
}
