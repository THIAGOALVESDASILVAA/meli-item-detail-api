package com.meli.itemdetail.infrastructure.adapter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.itemdetail.core.domain.model.RelatedProducts;
import com.meli.itemdetail.core.domain.port.RelatedProductsRepositoryPort;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import jakarta.annotation.PostConstruct;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Component
public class JsonRelatedProductsRepositoryAdapter implements RelatedProductsRepositoryPort {

    private final ObjectMapper objectMapper;
    private final Map<String, RelatedProducts> relatedCache = new ConcurrentHashMap<>();

    public JsonRelatedProductsRepositoryAdapter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    void loadData() {
        try {
            var resource = new ClassPathResource("mock-data/related.json");
            var relatedList = objectMapper.readValue(
                    resource.getInputStream(),
                    new TypeReference<List<Map<String, Object>>>() {
                    });

            relatedCache.putAll(relatedList.stream()
                    .collect(Collectors.toMap(
                            data -> (String) data.get("product_id"),
                            this::mapToRelatedProducts)));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load related products data", e);
        }
    }

    @Override
    public RelatedProducts findByProductId(String productId) {
        return relatedCache.getOrDefault(productId, new RelatedProducts());
    }

    @SuppressWarnings("unchecked")
    private RelatedProducts mapToRelatedProducts(Map<String, Object> data) {
        var related = new RelatedProducts();
        var relatedProductsData = (List<Map<String, Object>>) data.get("relatedProducts");

        if (relatedProductsData != null) {
            var products = relatedProductsData.stream()
                    .map(this::mapToRelatedProduct)
                    .collect(Collectors.toList());
            related.setProducts(products);
        }

        return related;
    }

    private RelatedProducts.RelatedProduct mapToRelatedProduct(Map<String, Object> data) {
        return new RelatedProducts.RelatedProduct(
                (String) data.get("id"),
                (String) data.get("title"),
                new BigDecimal(data.get("price").toString()),
                BigDecimal.ZERO, // discountPercentage
                null,
                (String) data.get("image"));
    }
}
