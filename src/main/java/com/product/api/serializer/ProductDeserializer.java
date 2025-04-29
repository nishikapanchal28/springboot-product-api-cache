package com.product.api.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.product.api.model.Product;

import java.io.IOException;
import java.util.UUID;

public class ProductDeserializer extends StdDeserializer<Product> {
    public ProductDeserializer() {
        this(null);
    }

    public ProductDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public Product deserialize(JsonParser parser, DeserializationContext ctxt) throws IOException {
        JsonNode node = parser.getCodec().readTree(parser);
        String name = node.get("productName").asText();
        String description = node.has("description") ? node.get("description").asText() : "";
        double price = node.get("price").asDouble();
        boolean available = node.get("available").asBoolean();
        return new Product(UUID.randomUUID(), name, description, price, available);
    }
}
