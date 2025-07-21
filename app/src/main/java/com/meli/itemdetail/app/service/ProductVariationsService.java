package com.meli.itemdetail.app.service;

import com.meli.itemdetail.core.domain.model.ProductVariation;
import com.meli.itemdetail.core.usecase.GetProductVariationsUseCase;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class ProductVariationsService {

    private final GetProductVariationsUseCase getProductVariationsUseCase;

    public ProductVariationsService(GetProductVariationsUseCase getProductVariationsUseCase) {
        this.getProductVariationsUseCase = getProductVariationsUseCase;
    }

    public List<ProductVariation> getProductVariations(String productId) {
        return getProductVariationsUseCase.execute(productId);
    }
}
