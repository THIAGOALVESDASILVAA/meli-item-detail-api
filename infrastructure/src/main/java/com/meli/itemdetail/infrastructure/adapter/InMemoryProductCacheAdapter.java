package com.meli.itemdetail.infrastructure.adapter;

import com.meli.itemdetail.core.domain.model.Product;
import com.meli.itemdetail.core.domain.port.ProductCachePort;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

@Repository
public class InMemoryProductCacheAdapter implements ProductCachePort {

    private final ConcurrentMap<String, Product> cache = new ConcurrentHashMap<>();

    @Override
    public Optional<Product> get(String key) {
        return Optional.ofNullable(cache.get(key));
    }

    @Override
    public void put(String key, Product value) {
        cache.put(key, value);
    }

    @Override
    public void remove(String key) {
        cache.remove(key);
    }
}
