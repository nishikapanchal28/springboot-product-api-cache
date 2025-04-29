package com.product.api.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import com.product.api.model.Product;

import java.io.IOException;

public class ProductSerializer extends StdSerializer<Product> {
    public ProductSerializer() {
        this(null);
    }

    public ProductSerializer(Class<Product> t) {
        super(t);
    }

    @Override
    public void serialize(Product product, JsonGenerator jsonGen, SerializerProvider provider) throws IOException {
        jsonGen.writeStartObject();
        jsonGen.writeStringField("productName", product.getName());
        jsonGen.writeStringField("description", product.getDescription());
        jsonGen.writeNumberField("price", product.getPrice());
        jsonGen.writeBooleanField("available", product.getAvailable());
        jsonGen.writeEndObject();
    }
}
