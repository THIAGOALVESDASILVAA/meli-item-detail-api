package com.meli.itemdetail.core.usecase;

import com.meli.itemdetail.core.domain.model.Product;
import com.meli.itemdetail.core.domain.port.ProductCachePort;
import com.meli.itemdetail.core.domain.port.ProductRepositoryPort;
import java.util.Optional;

public class GetProductByIdUseCase {

    private final ProductRepositoryPort productRepositoryPort;
    private final ProductCachePort productCachePort;

    public GetProductByIdUseCase(ProductRepositoryPort productRepositoryPort, ProductCachePort productCachePort) {
        this.productRepositoryPort = productRepositoryPort;
        this.productCachePort = productCachePort;
    }

    public Optional<Product> execute(String productId) {
        return productCachePort.get(productId)
                .or(() -> findAndCache(productId));
    }

    private Optional<Product> findAndCache(String productId) {
        Optional<Product> product = productRepositoryPort.findById(productId);
        product.ifPresent(p -> productCachePort.put(productId, p));
        return product;
    }
}
