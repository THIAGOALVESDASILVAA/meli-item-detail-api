package com.meli.itemdetail.core.domain.port;

import com.meli.itemdetail.core.domain.model.TechnicalSpecifications;

public interface TechnicalSpecificationsRepositoryPort {
    TechnicalSpecifications findByProductId(String productId);
}
