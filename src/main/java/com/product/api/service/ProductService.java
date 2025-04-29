package com.product.api.service;

import com.product.api.exception.ProductValidationException;
import com.product.api.model.Product;
import com.product.api.validator.ProductValidator;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ProductService {
    private final Map<UUID, Product> productMap = new ConcurrentHashMap<>();

    public Product createProduct(Product product) {
        UUID id = UUID.randomUUID();
        ProductValidator.validate(product);
        product.setId(id);
        productMap.put(id, product);
        return product;
    }

    public Product getProductById(UUID id) {
        return Optional.ofNullable(productMap.get(id))
                .orElseThrow(() -> new ProductValidationException("Product with ID " + id + " not found"));
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(productMap.values());
    }

    public Product updateProduct(UUID id, Product updatedProduct) {
        if (!productMap.containsKey(id)) {
            throw new ProductValidationException("Product with ID " + id + " not found");
        }
        ProductValidator.validate(updatedProduct);
        updatedProduct.setId(id);
        productMap.put(id, updatedProduct);
        return updatedProduct;
    }

    public void deleteProduct(UUID id) {
        if (!productMap.containsKey(id)) {
            throw new ProductValidationException("Product with ID " + id + " not found");
        }
        productMap.remove(id);
    }

    public void clearAll() {
        productMap.clear();
    }
}
