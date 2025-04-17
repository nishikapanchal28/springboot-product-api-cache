package com.product.api;

import com.product.api.dto.ProductDTO;
import com.product.api.mapper.ProductMapper;
import com.product.api.model.Product;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.UUID;

@SpringBootTest
class ApiApplicationTests {

	@Test
	void contextLoads() {
	}
	private final ProductMapper productMapper = ProductMapper.INSTANCE;

	@Test
	void testProductToProductDTO() {
		Product product = new Product();
		product.setId(UUID.randomUUID());
		product.setName("Test Product");
		product.setDescription("Description of test product");
		product.setPrice(19.99);
		product.setAvailable(true);

		ProductDTO productDTO = productMapper.productToProductDTO(product);

		assertEquals(product.getId(), productDTO.getId());
		assertEquals(product.getName(), productDTO.getName());
		assertEquals(product.getDescription(), productDTO.getDescription());
		assertEquals(product.getPrice(), productDTO.getPrice());
		assertEquals(product.getAvailable(), productDTO.getAvailable());
	}

	@Test
	void testProductDTOToProduct() {
		ProductDTO productDTO = new ProductDTO();
		productDTO.setId(UUID.randomUUID());
		productDTO.setName("Test Product");
		productDTO.setDescription("Description of test product");
		productDTO.setPrice(19.99);
		productDTO.setAvailable(true);

		Product product = productMapper.productDTOToProduct(productDTO);

		assertEquals(productDTO.getId(), product.getId());
		assertEquals(productDTO.getName(), product.getName());
		assertEquals(productDTO.getDescription(), product.getDescription());
		assertEquals(productDTO.getPrice(), product.getPrice());
		assertEquals(productDTO.getAvailable(), product.getAvailable());
	}
}
