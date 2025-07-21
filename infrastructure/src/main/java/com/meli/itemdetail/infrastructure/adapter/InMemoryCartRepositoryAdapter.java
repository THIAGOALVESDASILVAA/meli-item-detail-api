package com.meli.itemdetail.infrastructure.adapter;

import com.meli.itemdetail.core.domain.model.CartItem;
import com.meli.itemdetail.core.domain.port.CartRepositoryPort;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Repository
public class InMemoryCartRepositoryAdapter implements CartRepositoryPort {

    private final ConcurrentMap<String, CartItem> cartItems = new ConcurrentHashMap<>();

    @Override
    public void addToCart(CartItem cartItem) {
        cartItems.put(cartItem.getProductId(), cartItem);
    }
}
