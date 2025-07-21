package com.meli.itemdetail.infrastructure.adapter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.itemdetail.core.domain.model.TechnicalSpecifications;
import com.meli.itemdetail.core.domain.port.TechnicalSpecificationsRepositoryPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class JsonTechnicalSpecificationsRepositoryAdapter implements TechnicalSpecificationsRepositoryPort {

    private final ObjectMapper objectMapper;
    private final Map<String, TechnicalSpecifications> specsCache = new ConcurrentHashMap<>();

    public JsonTechnicalSpecificationsRepositoryAdapter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    void loadData() {
        try {
            var resource = new ClassPathResource("mock-data/technical-specs.json");
            var specsList = objectMapper.readValue(
                    resource.getInputStream(),
                    new TypeReference<List<Map<String, Object>>>() {
                    });

            specsCache.putAll(specsList.stream()
                    .collect(Collectors.toMap(
                            data -> (String) data.get("product_id"),
                            this::mapToTechnicalSpecs)));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load technical specs data", e);
        }
    }

    @Override
    public TechnicalSpecifications findByProductId(String productId) {
        return specsCache.get(productId);
    }

    @SuppressWarnings("unchecked")
    private TechnicalSpecifications mapToTechnicalSpecs(Map<String, Object> data) {
        var techSpecs = (Map<String, Object>) data.get("technicalSpecs");

        if (techSpecs == null) {
            return new TechnicalSpecifications();
        }

        var display = (Map<String, Object>) techSpecs.get("display");
        var memory = (Map<String, Object>) techSpecs.get("memory");
        var camera = (Map<String, Object>) techSpecs.get("camera");
        var features = (Map<String, Object>) techSpecs.get("features");

        return new TechnicalSpecifications(
                display != null ? (String) display.get("size") : null,
                memory != null ? (String) memory.get("storage") : null,
                memory != null ? (String) memory.get("ram") : null,
                camera != null ? (String) camera.get("rear") : null,
                camera != null ? (String) camera.get("front") : null,
                features != null ? (String) features.get("fingerprint") : null,
                features != null ? (Boolean) features.get("nfc") : null);
    }
}
