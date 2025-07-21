package com.meli.itemdetail.app.service;

import com.meli.itemdetail.core.domain.model.ProductShipping;
import com.meli.itemdetail.core.usecase.GetProductShippingUseCase;
import org.springframework.stereotype.Component;

@Component
public class ProductShippingService {

    private final GetProductShippingUseCase getProductShippingUseCase;

    public ProductShippingService(GetProductShippingUseCase getProductShippingUseCase) {
        this.getProductShippingUseCase = getProductShippingUseCase;
    }

    public ProductShipping getProductShipping(String productId) {
        return getProductShippingUseCase.execute(productId);
    }
}
