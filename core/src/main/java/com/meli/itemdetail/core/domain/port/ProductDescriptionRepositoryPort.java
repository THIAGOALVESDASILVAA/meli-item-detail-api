package com.meli.itemdetail.core.domain.port;

import com.meli.itemdetail.core.domain.model.ProductDescription;

public interface ProductDescriptionRepositoryPort {
    ProductDescription findByProductId(String productId);
}
