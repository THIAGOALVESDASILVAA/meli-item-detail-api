package com.meli.itemdetail.core.usecase;

import com.meli.itemdetail.core.domain.model.ProductShipping;
import com.meli.itemdetail.core.domain.port.ProductShippingRepositoryPort;

public class GetProductShippingUseCase {

    private final ProductShippingRepositoryPort productShippingRepositoryPort;

    public GetProductShippingUseCase(ProductShippingRepositoryPort productShippingRepositoryPort) {
        this.productShippingRepositoryPort = productShippingRepositoryPort;
    }

    public ProductShipping execute(String productId) {
        return productShippingRepositoryPort.findByProductId(productId);
    }
}
