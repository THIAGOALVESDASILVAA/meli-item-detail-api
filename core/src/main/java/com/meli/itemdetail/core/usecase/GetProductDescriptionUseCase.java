package com.meli.itemdetail.core.usecase;

import com.meli.itemdetail.core.domain.model.ProductDescription;
import com.meli.itemdetail.core.domain.port.ProductDescriptionRepositoryPort;

public class GetProductDescriptionUseCase {

    private final ProductDescriptionRepositoryPort productDescriptionRepositoryPort;

    public GetProductDescriptionUseCase(ProductDescriptionRepositoryPort productDescriptionRepositoryPort) {
        this.productDescriptionRepositoryPort = productDescriptionRepositoryPort;
    }

    public ProductDescription execute(String productId) {
        return productDescriptionRepositoryPort.findByProductId(productId);
    }
}
