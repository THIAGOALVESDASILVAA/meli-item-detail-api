package com.meli.itemdetail.core.usecase;

import com.meli.itemdetail.core.domain.model.Product;
import com.meli.itemdetail.core.domain.port.BrandProductsRepositoryPort;
import java.util.List;

public class GetBrandProductsUseCase {

    private final BrandProductsRepositoryPort brandProductsRepositoryPort;

    public GetBrandProductsUseCase(BrandProductsRepositoryPort brandProductsRepositoryPort) {
        this.brandProductsRepositoryPort = brandProductsRepositoryPort;
    }

    public List<Product> execute(String brandName) {
        return brandProductsRepositoryPort.findByBrandName(brandName);
    }
}
