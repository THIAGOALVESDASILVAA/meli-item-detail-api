package com.meli.itemdetail.core.domain.port;

import com.meli.itemdetail.core.domain.model.ProductSeller;

public interface ProductSellerRepositoryPort {
    ProductSeller findByProductId(String productId);
}
