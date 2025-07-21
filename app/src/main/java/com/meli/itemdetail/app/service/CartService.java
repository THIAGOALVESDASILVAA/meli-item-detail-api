package com.meli.itemdetail.app.service;

import com.meli.itemdetail.core.usecase.AddToCartUseCase;
import org.springframework.stereotype.Component;

@Component
public class CartService {

    private final AddToCartUseCase addToCartUseCase;

    public CartService(AddToCartUseCase addToCartUseCase) {
        this.addToCartUseCase = addToCartUseCase;
    }

    public void addToCart(String productId, Integer quantity) {
        addToCartUseCase.execute(productId, quantity);
    }
}
