package com.meli.itemdetail.app.service;

import com.meli.itemdetail.core.domain.model.ProductSeller;
import com.meli.itemdetail.core.usecase.GetProductSellerUseCase;
import org.springframework.stereotype.Component;

@Component
public class ProductSellerService {

    private final GetProductSellerUseCase getProductSellerUseCase;

    public ProductSellerService(GetProductSellerUseCase getProductSellerUseCase) {
        this.getProductSellerUseCase = getProductSellerUseCase;
    }

    public ProductSeller getProductSeller(String productId) {
        return getProductSellerUseCase.execute(productId);
    }
}
