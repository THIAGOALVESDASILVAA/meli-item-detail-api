package com.meli.itemdetail.core.usecase;

import com.meli.itemdetail.core.domain.model.CartItem;
import com.meli.itemdetail.core.domain.port.CartRepositoryPort;

public class AddToCartUseCase {

    private final CartRepositoryPort cartRepositoryPort;

    public AddToCartUseCase(CartRepositoryPort cartRepositoryPort) {
        this.cartRepositoryPort = cartRepositoryPort;
    }

    public void execute(String productId, int quantity) {
        CartItem cartItem = createCartItem(productId, quantity);
        cartRepositoryPort.addToCart(cartItem);
    }

    private CartItem createCartItem(String productId, int quantity) {
        CartItem cartItem = new CartItem();
        cartItem.setProductId(productId);
        cartItem.setQuantity(quantity);
        return cartItem;
    }
}
