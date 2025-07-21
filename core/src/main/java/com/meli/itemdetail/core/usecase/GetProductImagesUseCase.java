package com.meli.itemdetail.core.usecase;

import com.meli.itemdetail.core.domain.model.ProductImages;
import com.meli.itemdetail.core.domain.port.ProductImagesRepositoryPort;

public class GetProductImagesUseCase {

    private final ProductImagesRepositoryPort productImagesRepositoryPort;

    public GetProductImagesUseCase(ProductImagesRepositoryPort productImagesRepositoryPort) {
        this.productImagesRepositoryPort = productImagesRepositoryPort;
    }

    public ProductImages execute(String productId) {
        return productImagesRepositoryPort.findByProductId(productId)
                .orElse(new ProductImages());
    }
}
