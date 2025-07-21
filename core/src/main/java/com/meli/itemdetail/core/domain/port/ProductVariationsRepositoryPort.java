package com.meli.itemdetail.core.domain.port;

import com.meli.itemdetail.core.domain.model.ProductVariation;
import java.util.List;

public interface ProductVariationsRepositoryPort {
    List<ProductVariation> findByProductId(String productId);
}
