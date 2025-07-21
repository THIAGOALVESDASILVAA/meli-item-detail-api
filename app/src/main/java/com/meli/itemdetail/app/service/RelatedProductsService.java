package com.meli.itemdetail.app.service;

import com.meli.itemdetail.core.domain.model.RelatedProducts;
import com.meli.itemdetail.core.usecase.GetRelatedProductsUseCase;
import org.springframework.stereotype.Component;

@Component
public class RelatedProductsService {

    private final GetRelatedProductsUseCase getRelatedProductsUseCase;

    public RelatedProductsService(GetRelatedProductsUseCase getRelatedProductsUseCase) {
        this.getRelatedProductsUseCase = getRelatedProductsUseCase;
    }

    public RelatedProducts getRelatedProducts(String productId) {
        return getRelatedProductsUseCase.execute(productId);
    }
}
