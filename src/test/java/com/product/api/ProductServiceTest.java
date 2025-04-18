package com.product.api;

import com.product.api.exception.ProductNotFoundException;
import com.product.api.model.Product;
import com.product.api.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.UUID;

public class ProductServiceTest {
    private ProductService productService;

    @BeforeEach
    void setup() {
        productService = new ProductService();
    }

    @DisplayName("Create and get the products")
    @Test
    void testCreateAndGetProduct() {
        Product product = new Product(null, "Phone", "Smartphone", 699.99, true);
        Product created = productService.createProduct(product);

        Product fetched = productService.getProductById(created.getId());

        assertEquals("Phone", fetched.getName());
        assertTrue(fetched.getAvailable());
    }
    @DisplayName("Show the product is not found")
    @Test
    void testGetProductNotFound() {
        UUID id = UUID.randomUUID();
        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(id));
    }
    @DisplayName("Update the product")
    @Test
    void testUpdateProduct() {
        Product product = productService.createProduct(new Product(null, "TV", "4K TV", 999.0, true));
        product.setPrice(899.0);

        Product updated = productService.updateProduct(product.getId(), product);

        assertEquals(899.0, updated.getPrice());
    }
    @DisplayName("Delete a product")
    @Test
    void testDeleteProduct() {
        Product product = productService.createProduct(new Product(null, "Tablet", "Android", 299.0, true));
        productService.deleteProduct(product.getId());

        assertThrows(ProductNotFoundException.class, () -> productService.getProductById(product.getId()));
    }

    @DisplayName("Get all the products")
    @Test
    void testGetAllProducts() {
        productService.createProduct(new Product(null, "Item1", "Desc", 100.0, true));
        productService.createProduct(new Product(null, "Item2", "Desc", 200.0, true));

        List<Product> products = productService.getAllProducts();

        assertEquals(2, products.size());
    }

}
