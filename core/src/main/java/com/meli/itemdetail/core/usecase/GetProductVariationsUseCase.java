package com.meli.itemdetail.core.usecase;

import com.meli.itemdetail.core.domain.model.ProductVariation;
import com.meli.itemdetail.core.domain.port.ProductVariationsRepositoryPort;
import java.util.List;

public class GetProductVariationsUseCase {

    private final ProductVariationsRepositoryPort productVariationsRepositoryPort;

    public GetProductVariationsUseCase(ProductVariationsRepositoryPort productVariationsRepositoryPort) {
        this.productVariationsRepositoryPort = productVariationsRepositoryPort;
    }

    public List<ProductVariation> execute(String productId) {
        return productVariationsRepositoryPort.findByProductId(productId);
    }
}
