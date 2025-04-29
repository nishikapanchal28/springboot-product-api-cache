package com.product.api.exception;

public class ProductValidationException extends RuntimeException{

    public ProductValidationException(String message) {
        super(message);
    }
}
