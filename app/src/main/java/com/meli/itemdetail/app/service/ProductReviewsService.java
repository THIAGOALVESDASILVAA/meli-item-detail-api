package com.meli.itemdetail.app.service;

import com.meli.itemdetail.core.domain.model.ProductReviews;
import com.meli.itemdetail.core.usecase.GetProductReviewsUseCase;
import org.springframework.stereotype.Component;

@Component
public class ProductReviewsService {

    private final GetProductReviewsUseCase getProductReviewsUseCase;

    public ProductReviewsService(GetProductReviewsUseCase getProductReviewsUseCase) {
        this.getProductReviewsUseCase = getProductReviewsUseCase;
    }

    public ProductReviews getProductReviews(String productId) {
        return getProductReviewsUseCase.execute(productId);
    }
}
