package com.meli.itemdetail.infrastructure.adapter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.itemdetail.core.domain.model.ProductVariation;
import com.meli.itemdetail.core.domain.port.ProductVariationsRepositoryPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class JsonProductVariationsRepositoryAdapter implements ProductVariationsRepositoryPort {

    private final ObjectMapper objectMapper;
    private final Map<String, List<ProductVariation>> variationsCache = new ConcurrentHashMap<>();

    public JsonProductVariationsRepositoryAdapter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    void loadData() {
        try {
            var resource = new ClassPathResource("mock-data/variations.json");
            var variationsList = objectMapper.readValue(
                    resource.getInputStream(),
                    new TypeReference<List<Map<String, Object>>>() {
                    });

            variationsCache.putAll(variationsList.stream()
                    .collect(Collectors.toMap(
                            data -> (String) data.get("product_id"),
                            this::mapToProductVariations)));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load variations data", e);
        }
    }

    @Override
    public List<ProductVariation> findByProductId(String productId) {
        return variationsCache.getOrDefault(productId, Collections.emptyList());
    }

    @SuppressWarnings("unchecked")
    private List<ProductVariation> mapToProductVariations(Map<String, Object> data) {
        var variationsData = (List<Map<String, Object>>) data.get("variations");
        if (variationsData == null) {
            return Collections.emptyList();
        }

        var variation = new ProductVariation();
        var colors = new ArrayList<String>();
        var storageOptions = new ArrayList<String>();
        var ramOptions = new ArrayList<String>();

        for (var variationData : variationsData) {
            var type = (String) variationData.get("type");
            var options = (List<Map<String, Object>>) variationData.get("options");

            if (options != null) {
                for (var option : options) {
                    var name = (String) option.get("name");
                    var available = (Boolean) option.get("available");

                    if (Boolean.TRUE.equals(available) && name != null) {
                        switch (type) {
                            case "color" -> colors.add(name);
                            case "storage" -> storageOptions.add(name);
                            case "memory" -> ramOptions.add(name);
                        }
                    }
                }
            }
        }

        variation.setAvailableColors(colors);
        variation.setStorageOptions(storageOptions);
        variation.setRamOptions(ramOptions);

        return List.of(variation);
    }
}
