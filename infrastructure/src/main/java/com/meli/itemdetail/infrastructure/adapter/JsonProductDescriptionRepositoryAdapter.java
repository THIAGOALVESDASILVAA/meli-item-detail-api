package com.meli.itemdetail.infrastructure.adapter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.itemdetail.core.domain.model.ProductDescription;
import com.meli.itemdetail.core.domain.port.ProductDescriptionRepositoryPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class JsonProductDescriptionRepositoryAdapter implements ProductDescriptionRepositoryPort {

    private final ObjectMapper objectMapper;
    private final Map<String, ProductDescription> descriptionCache = new ConcurrentHashMap<>();

    public JsonProductDescriptionRepositoryAdapter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    void loadData() {
        try {
            var resource = new ClassPathResource("mock-data/descriptions.json");
            var descriptionsList = objectMapper.readValue(
                    resource.getInputStream(),
                    new TypeReference<List<Map<String, Object>>>() {
                    });

            descriptionCache.putAll(descriptionsList.stream()
                    .collect(Collectors.toMap(
                            data -> (String) data.get("product_id"),
                            this::mapToProductDescription)));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load descriptions data", e);
        }
    }

    @Override
    public ProductDescription findByProductId(String productId) {
        return descriptionCache.get(productId);
    }

    @SuppressWarnings("unchecked")
    private ProductDescription mapToProductDescription(Map<String, Object> data) {
        var description = new ProductDescription();
        var descriptionData = (Map<String, Object>) data.get("description");

        if (descriptionData == null) {
            return description;
        }

        var blocks = List.of(
                new ProductDescription.DescriptionBlock(
                        (String) descriptionData.get("title"),
                        (String) descriptionData.get("fullDescription"),
                        "main"),
                createFeaturesBlock((List<String>) descriptionData.get("features")),
                createWarrantyBlock((String) descriptionData.get("warranty")),
                createDimensionsBlock((Map<String, Object>) descriptionData.get("dimensions"))).stream()
                .filter(block -> block.getContent() != null && !block.getContent().isEmpty())
                .collect(Collectors.toList());

        description.setBlocks(blocks);
        return description;
    }

    private ProductDescription.DescriptionBlock createFeaturesBlock(List<String> features) {
        if (features == null || features.isEmpty()) {
            return new ProductDescription.DescriptionBlock("", "", "");
        }
        return new ProductDescription.DescriptionBlock(
                "Características Principais",
                String.join("\n• ", features),
                "features");
    }

    private ProductDescription.DescriptionBlock createWarrantyBlock(String warranty) {
        if (warranty == null || warranty.isEmpty()) {
            return new ProductDescription.DescriptionBlock("", "", "");
        }
        return new ProductDescription.DescriptionBlock(
                "Garantia",
                warranty,
                "warranty");
    }

    @SuppressWarnings("unchecked")
    private ProductDescription.DescriptionBlock createDimensionsBlock(Map<String, Object> dimensions) {
        if (dimensions == null || dimensions.isEmpty()) {
            return new ProductDescription.DescriptionBlock("", "", "");
        }

        var dimensionText = String.format(
                "Altura: %s | Largura: %s | Profundidade: %s | Peso: %s",
                dimensions.get("height"),
                dimensions.get("width"),
                dimensions.get("depth"),
                dimensions.get("weight"));

        return new ProductDescription.DescriptionBlock(
                "Dimensões",
                dimensionText,
                "dimensions");
    }
}
