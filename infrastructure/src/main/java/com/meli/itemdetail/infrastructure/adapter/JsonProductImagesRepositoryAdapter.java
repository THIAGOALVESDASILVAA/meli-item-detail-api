package com.meli.itemdetail.infrastructure.adapter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.itemdetail.core.domain.model.ProductImages;
import com.meli.itemdetail.core.domain.port.ProductImagesRepositoryPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class JsonProductImagesRepositoryAdapter implements ProductImagesRepositoryPort {

    private final ObjectMapper objectMapper;
    private final Map<String, ProductImages> imagesCache = new ConcurrentHashMap<>();

    public JsonProductImagesRepositoryAdapter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    void loadData() {
        try {
            var resource = new ClassPathResource("mock-data/images.json");
            var imagesList = objectMapper.readValue(
                    resource.getInputStream(),
                    new TypeReference<List<Map<String, Object>>>() {
                    });

            imagesCache.putAll(imagesList.stream()
                    .collect(Collectors.toMap(
                            data -> (String) data.get("product_id"),
                            this::mapToProductImages)));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load images data", e);
        }
    }

    @Override
    public Optional<ProductImages> findByProductId(String productId) {
        return Optional.ofNullable(imagesCache.get(productId));
    }

    private ProductImages mapToProductImages(Map<String, Object> data) {
        var images = new ProductImages();
        var imagesList = (List<Map<String, Object>>) data.get("images");

        if (imagesList != null) {
            var urls = imagesList.stream()
                    .map(img -> (String) img.get("url"))
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            images.setImages(urls);
        }

        return images;
    }
}
