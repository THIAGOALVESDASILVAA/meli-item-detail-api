package com.meli.itemdetail.app.service;

import com.meli.itemdetail.core.domain.model.Product;
import com.meli.itemdetail.core.usecase.GetProductByIdUseCase;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class ProductService {

    private final GetProductByIdUseCase getProductByIdUseCase;

    public ProductService(GetProductByIdUseCase getProductByIdUseCase) {
        this.getProductByIdUseCase = getProductByIdUseCase;
    }

    public Optional<Product> getProductById(String productId) {
        return getProductByIdUseCase.execute(productId);
    }
}
