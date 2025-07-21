package com.meli.itemdetail.core.domain.port;

import com.meli.itemdetail.core.domain.model.ProductImages;
import java.util.Optional;

public interface ProductImagesRepositoryPort {
    Optional<ProductImages> findByProductId(String productId);
}
