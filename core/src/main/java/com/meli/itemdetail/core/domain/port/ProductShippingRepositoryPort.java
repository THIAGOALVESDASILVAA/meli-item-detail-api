package com.meli.itemdetail.core.domain.port;

import com.meli.itemdetail.core.domain.model.ProductShipping;

public interface ProductShippingRepositoryPort {
    ProductShipping findByProductId(String productId);
}
