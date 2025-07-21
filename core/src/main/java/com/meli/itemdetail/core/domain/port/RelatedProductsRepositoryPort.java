package com.meli.itemdetail.core.domain.port;

import com.meli.itemdetail.core.domain.model.RelatedProducts;

public interface RelatedProductsRepositoryPort {
    RelatedProducts findByProductId(String productId);
}
