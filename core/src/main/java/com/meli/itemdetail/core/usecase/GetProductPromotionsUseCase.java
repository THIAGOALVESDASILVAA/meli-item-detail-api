package com.meli.itemdetail.core.usecase;

import com.meli.itemdetail.core.domain.model.ProductPromotions;
import com.meli.itemdetail.core.domain.port.ProductPromotionsRepositoryPort;

public class GetProductPromotionsUseCase {

    private final ProductPromotionsRepositoryPort productPromotionsRepositoryPort;

    public GetProductPromotionsUseCase(ProductPromotionsRepositoryPort productPromotionsRepositoryPort) {
        this.productPromotionsRepositoryPort = productPromotionsRepositoryPort;
    }

    public ProductPromotions execute(String productId) {
        ProductPromotions promotions = productPromotionsRepositoryPort.findByProductId(productId);

        // TODO: live

        return promotions;
    }
}
