package com.meli.itemdetail.core.usecase;

import com.meli.itemdetail.core.domain.model.RelatedProducts;
import com.meli.itemdetail.core.domain.port.RelatedProductsRepositoryPort;

public class GetRelatedProductsUseCase {

    private final RelatedProductsRepositoryPort relatedProductsRepositoryPort;

    public GetRelatedProductsUseCase(RelatedProductsRepositoryPort relatedProductsRepositoryPort) {
        this.relatedProductsRepositoryPort = relatedProductsRepositoryPort;
    }

    public RelatedProducts execute(String productId) {
        return relatedProductsRepositoryPort.findByProductId(productId);
    }
}
