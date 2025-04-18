package com.product.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.api.controller.ProductController;
import com.product.api.dto.ProductDTO;
import com.product.api.mapper.ProductMapper;
import com.product.api.mapper.ProductMapperImpl;
import com.product.api.model.Product;
import com.product.api.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
@Import(ProductMapperImpl.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductMapper productMapper;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;

    private ProductDTO productDTO;
    private Product product;
    private UUID id;

    @BeforeEach
    void setup() {
        id = UUID.randomUUID();
        productDTO = new ProductDTO(null, "Phone", "Smartphone", 699.0, true);
        product = new Product(id, "Phone", "Smartphone", 699.0, true);

    }
    @DisplayName("Create a new product")
    @Test
    void testCreateProduct() throws Exception {
        ProductDTO savedDto = new ProductDTO(id, "Laptop", "Gaming", 1299.0, true);
        Product productEntity = new Product(id, "Laptop", "Gaming", 1299.0, true);

        when(productMapper.productDTOToProduct(any())).thenReturn(productEntity);
        when(productService.createProduct(productEntity)).thenReturn(productEntity);
        when(productMapper.productToProductDTO(productEntity)).thenReturn(savedDto);

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Laptop"));
    }

    @DisplayName("Get the product by id")
    @Test
    void testGetProductById() throws Exception {
        when(productService.getProductById(id)).thenReturn(product);
        when(productMapper.productToProductDTO(product)).thenReturn(new ProductDTO(id, "Phone", "Smartphone", 699.0, true));

        mockMvc.perform(get("/api/products/" + id))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Phone"));
    }

    @DisplayName("List all the products")
    @Test
    void testListAllProducts() throws Exception {
        List<Product> productList = List.of(product);
        List<ProductDTO> productDTOList = List.of(new ProductDTO(id, "Phone", "Smartphone", 699.0, true));

        when(productService.getAllProducts()).thenReturn(productList);
        when(productMapper.productToProductDTO(product)).thenReturn(productDTOList.get(0));

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1))
                .andExpect(jsonPath("$[0].name").value("Phone"));
    }

    @DisplayName("Delete a product")
    @Test
    void testDeleteProduct() throws Exception {
        Mockito.doNothing().when(productService).deleteProduct(id);

        mockMvc.perform(delete("/api/products/" + id))
                .andExpect(status().isNoContent());
    }

    @DisplayName("Update the Product")
    @Test
    void testUpdateProduct() throws Exception {
        ProductDTO updatedDTO = new ProductDTO(id, "Phone", "Smartphone", 799.0, false);
        Product updatedProduct = new Product(id, "Phone", "Smartphone", 799.0, false);

        when(productMapper.productDTOToProduct(any())).thenReturn(updatedProduct);
        when(productService.updateProduct(id, updatedProduct)).thenReturn(updatedProduct);
        when(productMapper.productToProductDTO(updatedProduct)).thenReturn(updatedDTO);

        mockMvc.perform(put("/api/products/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updatedDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.price").value(799.0))
                .andExpect(jsonPath("$.available").value(false));
    }

    @DisplayName("Create a new product with availability false")
    @Test
    void testCreateProductWithAvailabilityFalse() throws Exception {
        ProductDTO productWithFalseAvailability = new ProductDTO(null, "Tablet", "Android", 499.0, false);
        Product productEntity = new Product(id, "Tablet", "Android", 499.0, false);

        when(productMapper.productDTOToProduct(any())).thenReturn(productEntity);
        when(productService.createProduct(productEntity)).thenReturn(productEntity);
        when(productMapper.productToProductDTO(productEntity)).thenReturn(productWithFalseAvailability);

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(productWithFalseAvailability)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.available").value(false));
    }

    @DisplayName("Update product with invalid data")
    @Test
    void testUpdateProductWithInvalidData() throws Exception {
        ProductDTO invalidProductDTO = new ProductDTO(id, "", "Smartphone", -100.0, true); // Invalid name and price

        mockMvc.perform(put("/api/products/" + id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidProductDTO)))
                .andExpect(status().isBadRequest());
    }

    @DisplayName("List all products when no products exist")
    @Test
    void testListAllProductsEmpty() throws Exception {
        when(productService.getAllProducts()).thenReturn(List.of()); // Simulate no products in the database

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(0)); // Assert empty list
    }

    @DisplayName("Create a new product with missing required fields")
    @Test
    void testCreateProductWithMissingFields() throws Exception {
        ProductDTO invalidProductDTO = new ProductDTO(null, "", "Smartphone", -1.0, true); // Missing name and invalid price

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidProductDTO)))
                .andExpect(status().isBadRequest());
    }

}
