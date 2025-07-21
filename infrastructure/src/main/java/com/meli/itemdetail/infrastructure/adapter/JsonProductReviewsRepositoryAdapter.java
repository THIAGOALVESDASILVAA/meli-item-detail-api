package com.meli.itemdetail.infrastructure.adapter;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meli.itemdetail.core.domain.model.ProductReviews;
import com.meli.itemdetail.core.domain.port.ProductReviewsRepositoryPort;
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
public class JsonProductReviewsRepositoryAdapter implements ProductReviewsRepositoryPort {

    private final ObjectMapper objectMapper;
    private final Map<String, ProductReviews> reviewsCache = new ConcurrentHashMap<>();

    public JsonProductReviewsRepositoryAdapter(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostConstruct
    void loadData() {
        try {
            var resource = new ClassPathResource("mock-data/reviews.json");
            var reviewsList = objectMapper.readValue(
                    resource.getInputStream(),
                    new TypeReference<List<Map<String, Object>>>() {
                    });

            reviewsCache.putAll(reviewsList.stream()
                    .collect(Collectors.toMap(
                            data -> (String) data.get("product_id"),
                            this::mapToProductReviews)));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load reviews data", e);
        }
    }

    @Override
    public ProductReviews findByProductId(String productId) {
        return reviewsCache.get(productId);
    }

    @SuppressWarnings("unchecked")
    private ProductReviews mapToProductReviews(Map<String, Object> data) {
        var reviews = new ProductReviews();
        var reviewsData = (List<Map<String, Object>>) data.get("reviews");

        if (reviewsData == null || reviewsData.isEmpty()) {
            return reviews;
        }

        var averageRating = reviewsData.stream()
                .mapToInt(review -> (Integer) review.get("rating"))
                .average()
                .orElse(0.0);

        reviews.setAverageRating(BigDecimal.valueOf(averageRating));
        reviews.setReviewCount(reviewsData.size());

        var comments = reviewsData.stream()
                .map(this::mapToComment)
                .collect(Collectors.toList());
        reviews.setComments(comments);

        return reviews;
    }

    @SuppressWarnings("unchecked")
    private ProductReviews.Comment mapToComment(Map<String, Object> data) {
        return new ProductReviews.Comment(
                (String) data.get("reviewer"),
                (Integer) data.get("rating"),
                (String) data.get("text"),
                (String) data.get("date"));
    }
}
