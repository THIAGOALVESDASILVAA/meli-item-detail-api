package com.meli.itemdetail.core.usecase;

import com.meli.itemdetail.core.domain.model.ProductReviews;
import com.meli.itemdetail.core.domain.port.ProductReviewsRepositoryPort;

public class GetProductReviewsUseCase {

    private final ProductReviewsRepositoryPort productReviewsRepositoryPort;

    public GetProductReviewsUseCase(ProductReviewsRepositoryPort productReviewsRepositoryPort) {
        this.productReviewsRepositoryPort = productReviewsRepositoryPort;
    }

    public ProductReviews execute(String productId) {
        return productReviewsRepositoryPort.findByProductId(productId);
    }
}
