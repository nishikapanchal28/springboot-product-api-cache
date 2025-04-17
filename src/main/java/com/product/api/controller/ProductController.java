package com.product.api.controller;

import com.product.api.dto.ProductDTO;
import com.product.api.mapper.ProductMapper;
import com.product.api.model.Product;
import com.product.api.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    @Operation(summary = "Create a new product", description = "Create a new product with given details")
    @PostMapping
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        Product saved = productService.createProduct(productMapper.productDTOToProduct(productDTO));
        return ResponseEntity.ok(productMapper.productToProductDTO(saved));
    }

    @Operation(summary = "Get a product by ID", description = "Get details of a product by ID")
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable UUID id) {
        Product product = productService.getProductById(id);
        return ResponseEntity.ok(productMapper.productToProductDTO(product));
    }

    @Operation(summary = "List all products", description = "Get a list of all products")
    @GetMapping
    public ResponseEntity<List<ProductDTO>> listProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products.stream().map(productMapper::productToProductDTO).collect(Collectors.toList()));
    }

    @Operation(summary = "Update an existing product", description = "Update an existing product")
    @PutMapping("/{id}")
    public ResponseEntity<ProductDTO> updateProduct(
            @PathVariable UUID id,
            @Valid @RequestBody ProductDTO productDTO) {

        Product updated = productService.updateProduct(id, productMapper.productDTOToProduct(productDTO));
        return ResponseEntity.ok(productMapper.productToProductDTO(updated));
    }

    @Operation(summary = "Delete a product", description = "Delete a product by ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable UUID id) {
        productService.deleteProduct(id);
        return ResponseEntity.noContent().build();
    }

}
