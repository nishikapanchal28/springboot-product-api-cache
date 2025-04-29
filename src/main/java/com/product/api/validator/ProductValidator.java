package com.product.api.validator;

import com.product.api.exception.ProductValidationException;
import com.product.api.model.Product;

import java.util.UUID;

public class ProductValidator {
    public static void validate(Product product) {
            if (product == null) {
                throw new ProductValidationException("Product cannot be null.");
            }

            validateId(product.getId());
            validateName(product.getName());
            validateDescription(product.getDescription());
            validatePrice(product.getPrice());
            validateAvailability(product.getAvailable());
        }

    private static void validateId(UUID id) {
        if (id != null && id.toString().length() != 36) {
            throw new ProductValidationException("Product ID must be a valid UUID.");
        }
    }

    private static void validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new ProductValidationException("Product name is required.");
        }
        if (!name.contains("IT-Care")) {
            throw new ProductValidationException("Product name must contain 'IT-Care'.");
        }
        if (name.length() > 100) {
            throw new ProductValidationException("Product name must not exceed 100 characters.");
        }
    }

    private static void validateDescription(String description) {
        if (description != null) {
            if (description.length() < 10) {
                throw new ProductValidationException("Description must be at least 10 characters.");
            }
            if (description.length() > 500) {
                throw new ProductValidationException("Description must not exceed 500 characters.");
            }
        }
    }

    private static void validatePrice(Double price) {
        if (price == null) {
            throw new ProductValidationException("Price is required.");
        }
        if (price <= 0) {
            throw new ProductValidationException("Price must be greater than zero.");
        }
        if (price > 1_000_000) {
            throw new ProductValidationException("Price must not exceed 1,000,000.");
        }
    }

    private static void validateAvailability(Boolean available) {
        if (available == null) {
            throw new ProductValidationException("Availability must be specified (true/false).");
        }
    }
}
