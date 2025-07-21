package com.meli.itemdetail.core.domain.port;

import com.meli.itemdetail.core.domain.model.ProductPromotions;

public interface ProductPromotionsRepositoryPort {
    ProductPromotions findByProductId(String productId);
}
