package com.meli.itemdetail.core.domain.port;

import com.meli.itemdetail.core.domain.model.ProductReviews;

public interface ProductReviewsRepositoryPort {
    ProductReviews findByProductId(String productId);
}
