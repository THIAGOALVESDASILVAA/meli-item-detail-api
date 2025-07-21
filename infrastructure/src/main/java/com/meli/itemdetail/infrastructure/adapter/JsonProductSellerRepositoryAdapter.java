package com.meli.itemdetail.infrastructure.adapter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.itemdetail.core.domain.model.ProductSeller;
import com.meli.itemdetail.core.domain.port.ProductSellerRepositoryPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class JsonProductSellerRepositoryAdapter implements ProductSellerRepositoryPort {

    private final ObjectMapper objectMapper;
    private final Map<String, ProductSeller> sellerCache = new ConcurrentHashMap<>();

    public JsonProductSellerRepositoryAdapter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    void loadData() {
        try {
            var resource = new ClassPathResource("mock-data/seller.json");
            var sellerList = objectMapper.readValue(
                    resource.getInputStream(),
                    new TypeReference<List<Map<String, Object>>>() {
                    });

            sellerCache.putAll(sellerList.stream()
                    .collect(Collectors.toMap(
                            data -> (String) data.get("product_id"),
                            this::mapToProductSeller)));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load seller data", e);
        }
    }

    @Override
    public ProductSeller findByProductId(String productId) {
        return sellerCache.getOrDefault(productId, new ProductSeller());
    }

    @SuppressWarnings("unchecked")
    private ProductSeller mapToProductSeller(Map<String, Object> data) {
        var seller = (Map<String, Object>) data.get("seller");

        if (seller == null) {
            return new ProductSeller();
        }

        var metrics = (Map<String, Object>) seller.get("metrics");
        var registration = (Map<String, Object>) seller.get("registration");

        return new ProductSeller(
                (String) seller.get("name"),
                metrics != null ? ((Number) metrics.get("salesCount")).intValue() : 0,
                registration != null ? (String) registration.get("businessType") : "",
                0);
    }
}
