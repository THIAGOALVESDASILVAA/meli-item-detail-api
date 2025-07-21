package com.meli.itemdetail.core.domain.port;

import com.meli.itemdetail.core.domain.model.Product;
import java.util.Optional;

public interface ProductCachePort {
    Optional<Product> get(String key);
    void put(String key, Product value);
    void remove(String key);
}
