package com.meli.itemdetail.infrastructure.adapter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.itemdetail.core.domain.model.ProductShipping;
import com.meli.itemdetail.core.domain.port.ProductShippingRepositoryPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class JsonProductShippingRepositoryAdapter implements ProductShippingRepositoryPort {

    private final ObjectMapper objectMapper;
    private final Map<String, ProductShipping> shippingCache = new ConcurrentHashMap<>();

    public JsonProductShippingRepositoryAdapter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    void loadData() {
        try {
            var resource = new ClassPathResource("mock-data/shipping.json");
            var shippingList = objectMapper.readValue(
                    resource.getInputStream(),
                    new TypeReference<List<Map<String, Object>>>() {
                    });

            shippingCache.putAll(shippingList.stream()
                    .collect(Collectors.toMap(
                            data -> (String) data.get("product_id"),
                            this::mapToProductShipping)));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load shipping data", e);
        }
    }

    @Override
    public ProductShipping findByProductId(String productId) {
        return shippingCache.get(productId);
    }

    @SuppressWarnings("unchecked")
    private ProductShipping mapToProductShipping(Map<String, Object> data) {
        var shippingData = (Map<String, Object>) data.get("shipping");

        if (shippingData == null) {
            return new ProductShipping(false, 0, "", "");
        }

        var origin = (Map<String, Object>) shippingData.get("origin");
        var methods = (List<Map<String, Object>>) shippingData.get("methods");

        var originText = origin != null ? String.format("%s - %s", origin.get("city"), origin.get("state")) : "";

        var estimatedDays = methods != null && !methods.isEmpty() ? (Integer) methods.get(0).get("estimatedDays") : 0;

        var carrier = methods != null && !methods.isEmpty() ? (String) methods.get(0).get("name") : "";

        return new ProductShipping(
                (Boolean) shippingData.get("freeShipping"),
                estimatedDays,
                originText,
                carrier);
    }
}
