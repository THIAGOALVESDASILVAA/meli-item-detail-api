package com.meli.itemdetail.app.service;

import com.meli.itemdetail.core.domain.model.ProductImages;
import com.meli.itemdetail.core.usecase.GetProductImagesUseCase;
import org.springframework.stereotype.Component;

@Component
public class ProductImagesService {

    private final GetProductImagesUseCase getProductImagesUseCase;

    public ProductImagesService(GetProductImagesUseCase getProductImagesUseCase) {
        this.getProductImagesUseCase = getProductImagesUseCase;
    }

    public ProductImages getProductImages(String productId) {
        return getProductImagesUseCase.execute(productId);
    }
}
