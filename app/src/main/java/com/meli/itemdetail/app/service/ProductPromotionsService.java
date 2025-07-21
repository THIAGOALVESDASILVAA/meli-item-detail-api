package com.meli.itemdetail.app.service;

import com.meli.itemdetail.core.domain.model.ProductPromotions;
import com.meli.itemdetail.core.usecase.GetProductPromotionsUseCase;
import org.springframework.stereotype.Component;

@Component
public class ProductPromotionsService {

    private final GetProductPromotionsUseCase getProductPromotionsUseCase;

    public ProductPromotionsService(GetProductPromotionsUseCase getProductPromotionsUseCase) {
        this.getProductPromotionsUseCase = getProductPromotionsUseCase;
    }

    public ProductPromotions getProductPromotions(String productId) {
        return getProductPromotionsUseCase.execute(productId);
    }
}
