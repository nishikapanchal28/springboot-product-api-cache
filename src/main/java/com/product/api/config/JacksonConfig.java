package com.product.api.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.product.api.model.Product;
import com.product.api.serializer.ProductDeserializer;
import com.product.api.serializer.ProductSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JacksonConfig {
    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Product.class, new ProductSerializer());
        module.addDeserializer(Product.class, new ProductDeserializer());
        mapper.registerModule(module);
        return mapper;
    }

}
