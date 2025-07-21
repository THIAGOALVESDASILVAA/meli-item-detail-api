package com.meli.itemdetail.core.domain.port;

import com.meli.itemdetail.core.domain.model.CartItem;

public interface CartRepositoryPort {
    void addToCart(CartItem cartItem);
}
