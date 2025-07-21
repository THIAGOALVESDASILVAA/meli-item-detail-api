package com.meli.itemdetail.infrastructure.adapter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.itemdetail.core.domain.model.ProductPromotions;
import com.meli.itemdetail.core.domain.port.ProductPromotionsRepositoryPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class JsonProductPromotionsRepositoryAdapter implements ProductPromotionsRepositoryPort {

    private final ObjectMapper objectMapper;
    private final Map<String, ProductPromotions> promotionsCache = new ConcurrentHashMap<>();

    public JsonProductPromotionsRepositoryAdapter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    void loadData() {
        try {
            var resource = new ClassPathResource("mock-data/promotions.json");
            var promotionsList = objectMapper.readValue(
                    resource.getInputStream(),
                    new TypeReference<List<Map<String, Object>>>() {
                    });

            promotionsCache.putAll(promotionsList.stream()
                    .collect(Collectors.toMap(
                            data -> (String) data.get("product_id"),
                            this::mapToProductPromotions)));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load promotions data", e);
        }
    }

    @Override
    public ProductPromotions findByProductId(String productId) {
        return promotionsCache.get(productId);
    }

    @SuppressWarnings("unchecked")
    private ProductPromotions mapToProductPromotions(Map<String, Object> data) {
        var promotions = new ProductPromotions();
        var promotionsData = (List<Map<String, Object>>) data.get("promotions");

        if (promotionsData != null) {
            var coupons = promotionsData.stream()
                    .filter(promotion -> (Boolean) promotion.getOrDefault("active", false))
                    .map(this::mapToCoupon)
                    .collect(Collectors.toList());
            promotions.setCoupons(coupons);
        }

        return promotions;
    }

    private ProductPromotions.Coupon mapToCoupon(Map<String, Object> data) {
        return new ProductPromotions.Coupon(
                (String) data.get("title"),
                (String) data.get("description"),
                (String) data.get("type"),
                "");
    }
}
