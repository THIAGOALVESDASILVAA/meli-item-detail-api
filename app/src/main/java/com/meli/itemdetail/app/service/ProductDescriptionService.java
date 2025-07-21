package com.meli.itemdetail.app.service;

import com.meli.itemdetail.core.domain.model.ProductDescription;
import com.meli.itemdetail.core.usecase.GetProductDescriptionUseCase;
import org.springframework.stereotype.Component;

@Component
public class ProductDescriptionService {

    private final GetProductDescriptionUseCase getProductDescriptionUseCase;

    public ProductDescriptionService(GetProductDescriptionUseCase getProductDescriptionUseCase) {
        this.getProductDescriptionUseCase = getProductDescriptionUseCase;
    }

    public ProductDescription getProductDescription(String productId) {
        return getProductDescriptionUseCase.execute(productId);
    }
}
