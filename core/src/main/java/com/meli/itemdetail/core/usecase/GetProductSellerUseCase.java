package com.meli.itemdetail.core.usecase;

import com.meli.itemdetail.core.domain.model.ProductSeller;
import com.meli.itemdetail.core.domain.port.ProductSellerRepositoryPort;

public class GetProductSellerUseCase {

    private final ProductSellerRepositoryPort productSellerRepositoryPort;

    public GetProductSellerUseCase(ProductSellerRepositoryPort productSellerRepositoryPort) {
        this.productSellerRepositoryPort = productSellerRepositoryPort;
    }

    public ProductSeller execute(String productId) {
        return productSellerRepositoryPort.findByProductId(productId);
    }
}
