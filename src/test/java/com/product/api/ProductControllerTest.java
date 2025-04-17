package com.product.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.product.api.controller.ProductController;
import com.product.api.dto.ProductDTO;
import com.product.api.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private UUID id;
    private ProductDTO product;

    @BeforeEach
    void setup() {
        id = UUID.randomUUID();
        product = new ProductDTO(null, "Laptop", "Gaming", 1299.0, true);
    }

  /*  @Test
    void testCreateProduct() throws Exception {
        ProductDTO saved = new ProductDTO(id, "Laptop", "Gaming", 1299.0, true);
        when(productService.createProduct(any())).thenReturn(saved.toEntity());

        mockMvc.perform(post("/api/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(product)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Laptop"));
    }*/

  /*  @Test
    void testGetAllProducts() throws Exception {
        when(productService.getAllProducts()).thenReturn(List.of(product.toEntity()));

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(1));
    }*/
}
